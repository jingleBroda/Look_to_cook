package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.viewModel

import androidx.lifecycle.ViewModel
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.presentation.singleActivity.mvvm.utils.transPair.TransPair

abstract class BaseSearchRecipeFragmentViewModel:ViewModel() {
    abstract fun searchRecipe(getParams: AdvancedSearchGetParams)
    abstract fun translateWord(word:String, transPair: TransPair = TransPair.RuEnTransPair())
    abstract fun stopSearchRecipe()
    abstract fun emitEmptyHits()
}