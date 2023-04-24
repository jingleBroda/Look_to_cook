package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.Recipe
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.FragmentShowRecipeBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.DetailsRecipeDialogFragment
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.handlers.SearchRecipesHandler
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.RecipeRecViewAdapter
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.viewModel.ShowRecipeFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.utils.baseUiClasses.BaseFragment
import com.jinglebroda.presentation.singleActivity.mvvm.utils.extantionFun.closeKeyBoard
import com.jinglebroda.presentation.singleActivity.mvvm.utils.factory.ViewModelFactory
import com.jinglebroda.presentation.singleActivity.mvvm.utils.gridSpacingItemDecoration.GridSpacingItemDecoration
import com.jinglebroda.presentation.singleActivity.mvvm.utils.letterLanguage.LetterRusAndEngLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ShowRecipeFragment : BaseFragment(R.layout.fragment_show_recipe), SearchView.OnQueryTextListener{
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ShowRecipeFragmentViewModel
    private lateinit var binding:FragmentShowRecipeBinding
    private lateinit var adapterRecView:RecipeRecViewAdapter
    private lateinit var searchHandler:SearchRecipesHandler.Base

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentShowRecipeBinding.bind(view)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[ShowRecipeFragmentViewModel::class.java]
        adapterRecView = RecipeRecViewAdapter(this)
        with(binding){
            searchHandler = SearchRecipesHandler.Base(
                emptyRecipeWarning,
                searchProgress,
                recipeRecycler,
            )

            val hits = ShowRecipeFragmentArgs.fromBundle(requireArguments()).hitsParcelize
            searchRecipeView.queryHint = hits?.q
            recipeRecycler.layoutManager = GridLayoutManager(context, 1)
            recipeRecycler.addItemDecoration(
                GridSpacingItemDecoration(1,50,true)
            )
            recipeRecycler.hasFixedSize()
            recipeRecycler.adapter = adapterRecView
            if(hits != null){
                adapterRecView.updateListItemView(hits.getHits())
            }
            else{
                searchHandler.clearRecipe()
            }

            searchRecipeView.setOnQueryTextListener(this@ShowRecipeFragment)

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                viewModel.searchFlow.collect{ hits->
                    if(hits != Hits.generateEmptyHits()){
                        if(hits.hits.isNotEmpty()){
                            adapterRecView.updateListItemView(hits)
                            searchHandler.showRecipe()
                            Log.d("RefactoringSearchRecipe", hits.toString())
                        }
                        else{
                            searchHandler.clearRecipe()
                            Toast.makeText(
                                requireContext(),
                                requireContext().resources.getString(
                                    R.string.bad_search_recipe_string
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.translateFlow.collect{ translateResponse->
                    if(translateResponse != TranslateWordResponse.createEmptyResponse()){
                        viewModel.searchRecipe(
                            AdvancedSearchGetParams(
                                translateResponse.responseData.translatedText,
                                null,
                                null
                            )
                        )
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onQueryTextSubmit(recipe: String): Boolean {
        //ищем первый символ (букву)
        for(i in recipe){
            if(LetterRusAndEngLanguage.isEnglishLetter(i)){
                //если символ англ. делаем поиск запроса сразу
                searchHandler.searchRecipe()
                viewModel.searchRecipe(
                    AdvancedSearchGetParams(
                        recipe,
                        null,
                        null
                    )
                )
                closeKeyBoard(binding.searchRecipeView.windowToken)
                break
            }
            else{
                if(LetterRusAndEngLanguage.isRussianLetter(i)){
                    //если на русском, считаем, что поиск идет на русском языке, значит переводим вводимый текст на англ и только потом делаем поиск
                    searchHandler.searchRecipe()
                    viewModel.translateWord(recipe)
                    closeKeyBoard(binding.searchRecipeView.windowToken)
                    break
                }
            }
        }

        return true
    }

    override fun onQueryTextChange(recipe: String): Boolean {
        //удаляем последователность из пробелов в начале ввода
        if((recipe == " ")||(recipe == "  ")) binding.searchRecipeView.setQuery("",false)
        return true
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.deatilsConcreteRecipe->{
                val recipe = v.tag as Recipe
                val dialog = DetailsRecipeDialogFragment(
                    recipe.ingredients,
                    recipe.totalNutrients
                )
                dialog.show(parentFragmentManager, "DetailsRecipeDialogFragment")
            }
        }
    }
}