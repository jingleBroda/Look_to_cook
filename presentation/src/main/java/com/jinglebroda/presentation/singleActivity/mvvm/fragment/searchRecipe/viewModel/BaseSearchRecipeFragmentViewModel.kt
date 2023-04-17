package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.viewModel

import androidx.lifecycle.ViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.utils.transPair.TransPair

abstract class BaseSearchRecipeFragmentViewModel:ViewModel() {
    abstract fun searchRecipe(nameRecipe:String)
    abstract fun translateWord(word:String, transPair: TransPair = TransPair.RuEnTransPair())
    abstract fun stopSearchRecipe()
    abstract fun emitEmptyHits()
}