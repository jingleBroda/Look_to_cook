package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.utils.requestPacker

import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch.DataAdvancedSearch

abstract class SearchRecipeRequestPacker(
    protected val ingredientsString: String,
    protected val advancedSearch: DataAdvancedSearch?
) {
    abstract fun getRequest(): AdvancedSearchGetParams

    class Base(
        ingredientsString: String,
        advancedSearch: DataAdvancedSearch?
    ):SearchRecipeRequestPacker(ingredientsString, advancedSearch){
        override fun getRequest(): AdvancedSearchGetParams = AdvancedSearchGetParams(
            ingredientsString,
            advancedSearch?.getRangeCalories(),
            advancedSearch?.diet
        )
    }
}