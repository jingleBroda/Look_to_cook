package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.FragmentSearchRecipeBinding
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.navigator
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.handlers.SearchProcessingHandler
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.viewModel.SearchRecipeFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.ShowRecipeFragmentArgs
import com.jinglebroda.presentation.singleActivity.mvvm.utils.baseUiClasses.BaseFragment
import com.jinglebroda.presentation.singleActivity.mvvm.utils.factory.ViewModelFactory
import com.jinglebroda.presentation.singleActivity.mvvm.utils.letterLanguage.LetterRusAndEngLanguage
import com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize.HitsParcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRecipeFragment : BaseFragment(R.layout.fragment_search_recipe) {
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    private lateinit var viewModel: SearchRecipeFragmentViewModel
    private lateinit var binding: FragmentSearchRecipeBinding
    private lateinit var searchHandler: SearchProcessingHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSearchRecipeBinding.bind(view)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[SearchRecipeFragmentViewModel::class.java]
        searchHandler = SearchProcessingHandler.Base(
            binding.progressBar,
            binding.searchRecipeButton
        )
        with(binding){
            searchRecipeButton.setOnClickListener(this@SearchRecipeFragment)

            inputFieldIngredients.setHorizontallyScrolling(false)
            inputFieldIngredients.maxLines = Integer.MAX_VALUE
            inputFieldIngredients.imeOptions = EditorInfo.IME_ACTION_DONE
            inputFieldIngredients.setRawInputType(InputType.TYPE_CLASS_TEXT)

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                viewModel.searchFlow.collect{ hits->
                    Log.d("Hits", hits.toString())
                    if(hits != Hits.generateEmptyHits()){
                        if(hits.hits.isNotEmpty()){
                            viewModel.emitEmptyHits()
                            navigator().nextNotAddToBackstack(
                                R.id.action_searchRecipeFragment_to_showRecipeFragment,
                                R.id.searchRecipeFragment,
                                ShowRecipeFragmentArgs(
                                    HitsParcelize.createHitsParcelize(hits)
                                ).toBundle()
                            )
                        }
                        else{
                            searchHandler.showButton()
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
                    Log.d("www", translateResponse.toString())
                    if(translateResponse != TranslateWordResponse.createEmptyResponse()){
                        viewModel.searchRecipe(
                            translateResponse.responseData.translatedText
                        )
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.searchRecipeButton->{
                if(binding.inputFieldIngredients.text.isNotEmpty()){
                    val recipe = binding.inputFieldIngredients.text.toString()
                    for(i in recipe){
                        if( LetterRusAndEngLanguage.isEnglishLetter(i)){
                            //если символ англ. делаем поиск запроса сразу
                            Log.d("testFirsLetterRecipe", "En")
                            searchHandler.hideButton()
                            viewModel.searchRecipe(recipe)
                            break
                        }
                        else{
                            if(LetterRusAndEngLanguage.isRussianLetter(i)){
                                //если на русском, считаем, что поиск идет на русском языке, значит переводим вводимый текст на англ и только потом делаем поиск
                                Log.d("testFirsLetterRecipe", "Ru")
                                searchHandler.hideButton()
                                viewModel.translateWord(recipe)
                                break
                            }
                        }
                    }
                }
            }
        }
    }
}