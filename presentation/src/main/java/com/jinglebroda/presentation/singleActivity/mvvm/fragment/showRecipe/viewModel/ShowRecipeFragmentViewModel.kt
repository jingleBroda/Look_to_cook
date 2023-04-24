package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.viewModel

import androidx.lifecycle.viewModelScope
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import com.jinglebroda.domain.usecase.ApiDefaultSearchUseCase
import com.jinglebroda.domain.usecase.TranslateWordUseCase
import com.jinglebroda.presentation.singleActivity.mvvm.utils.transPair.TransPair
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowRecipeFragmentViewModel @Inject constructor(
    private val apiDefaultSearchUseCase:ApiDefaultSearchUseCase,
    private val translateUseCase:TranslateWordUseCase
):BaseShowRecipeFragmentViewModel() {
    private val _searchFlow:MutableStateFlow<Hits> = MutableStateFlow(Hits.generateEmptyHits())
    val searchFlow:StateFlow<Hits> = _searchFlow.asStateFlow()

    private val _translateFlow:MutableStateFlow<TranslateWordResponse> =
        MutableStateFlow(TranslateWordResponse.createEmptyResponse())
    val translateFlow:StateFlow<TranslateWordResponse> = _translateFlow.asStateFlow()

    override fun searchRecipe(getParams: AdvancedSearchGetParams) {
        viewModelScope.launch {
            val searchResult = apiDefaultSearchUseCase.invoke(getParams)
            _searchFlow.emit(searchResult.await())
        }
    }

    override fun translateWord(word: String, transPair: TransPair) {
        viewModelScope.launch {
            val translateResult = translateUseCase.invoke(word,transPair.value)
            _translateFlow.emit(translateResult.await())
        }
    }

    override fun stopSearchRecipe() {
        if(viewModelScope.isActive) viewModelScope.cancel()
    }
}