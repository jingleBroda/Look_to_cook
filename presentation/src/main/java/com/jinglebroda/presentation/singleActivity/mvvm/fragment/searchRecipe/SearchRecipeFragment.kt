package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.FragmentSearchRecipeBinding
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.internetConnection
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.navigator
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.AdvancedSearchSettingsDialog
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.closeDialogListener.AdvancedSearchSettingsDialogCloseListener
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch.DataAdvancedSearch
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch.DataAdvancedSearchParcelize
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.utils.handlers.SearchProcessingHandler
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.utils.requestPacker.SearchRecipeRequestPacker
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.viewModel.SearchRecipeFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.ShowRecipeFragmentArgs
import com.jinglebroda.presentation.singleActivity.mvvm.utils.baseUiClasses.BaseFragment
import com.jinglebroda.presentation.singleActivity.mvvm.utils.factory.ViewModelFactory
import com.jinglebroda.presentation.singleActivity.mvvm.utils.letterLanguage.LetterRusAndEngLanguage
import com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize.HitsParcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRecipeFragment : BaseFragment(R.layout.fragment_search_recipe),
    AdvancedSearchSettingsDialogCloseListener {
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    private val viewModel by viewModels<SearchRecipeFragmentViewModel>{
        viewModelFactory
    }
    private lateinit var binding: FragmentSearchRecipeBinding
    private lateinit var searchHandler: SearchProcessingHandler
    private var advancedSettingSearch:DataAdvancedSearch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        advancedSettingSearch = savedInstanceState?.getParcelable<DataAdvancedSearchParcelize>(
            keyAdvancedSettingSearch)?.getDataAdvancedSearch()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSearchRecipeBinding.bind(view)
        searchHandler = SearchProcessingHandler.Base(
            binding.progressBar,
            binding.searchRecipeButton,
            binding.customizeSearchTitle,
            binding.customizeSearchImg
        )
        with(binding){
            searchRecipeButton.setOnClickListener(this@SearchRecipeFragment)
            customizeSearchTitle.setOnClickListener(this@SearchRecipeFragment)
            customizeSearchImg.setOnClickListener(this@SearchRecipeFragment)

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
                            navigator().back()
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.translateFlow.collect{ translateResponse->
                    if(translateResponse != TranslateWordResponse.createEmptyResponse()){
                        val requestPacker = SearchRecipeRequestPacker.Base(
                            translateResponse.responseData.translatedText,
                            advancedSettingSearch
                        )
                        viewModel.searchRecipe(
                            requestPacker.getRequest()
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
                if(internetConnection().isInternetConnected()){
                    if(binding.inputFieldIngredients.text.isNotEmpty()){
                        val ingredients = binding.inputFieldIngredients.text.toString()
                        for(i in ingredients){
                            if( LetterRusAndEngLanguage.isEnglishLetter(i)){
                                //если символ англ. делаем поиск запроса сразу
                                Log.d("testFirsLetterRecipe", "En")
                                searchHandler.hideButton()
                                val requestPacker = SearchRecipeRequestPacker.Base(
                                    ingredients,
                                    advancedSettingSearch
                                )
                                viewModel.searchRecipe(
                                    requestPacker.getRequest()
                                )
                                break
                            }
                            else{
                                if(LetterRusAndEngLanguage.isRussianLetter(i)){
                                    //если на русском, считаем, что поиск идет на русском языке, значит переводим вводимый текст на англ и только потом делаем поиск
                                    Log.d("testFirsLetterRecipe", "Ru")
                                    searchHandler.hideButton()
                                    viewModel.translateWord(ingredients)
                                    break
                                }
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(
                        requireContext(),
                        requireContext().getString(R.string.internet_connection_error_string),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            R.id.customizeSearchTitle, R.id.customizeSearchImg->{
                if(advancedSettingSearch!=null){
                    AdvancedSearchSettingsDialog(
                        this,
                        advancedSettingSearch
                    ).show(parentFragmentManager, "AdvancedSearchSettingsDialog")
                }
                else{
                    AdvancedSearchSettingsDialog(this).show(
                        parentFragmentManager,
                        "AdvancedSearchSettingsDialog"
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(advancedSettingSearch!=null){
            outState.putParcelable(
                keyAdvancedSettingSearch,
                DataAdvancedSearchParcelize.createDataAdvancedSearchParcelize(
                    advancedSettingSearch!!
                )
            )
        }
        super.onSaveInstanceState(outState)
    }

    override fun closeDialog(selectedData: DataAdvancedSearch) {
        advancedSettingSearch = selectedData
        Log.d("testDialog", advancedSettingSearch.toString())
    }

    companion object{
        private const val keyAdvancedSettingSearch = "keyAdvancedSettingSearch"
    }
}