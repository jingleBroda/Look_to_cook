package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment.copyTextHelper

import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString

abstract class DetailsRecipeCopyTextHelper {
    abstract fun convertDataToString(data:List<RecipeDoubleString>):String

    class Base():DetailsRecipeCopyTextHelper(){
        override fun convertDataToString(data: List<RecipeDoubleString>): String {
            var result = ""
            data.forEach { i->
                result = result+"${i.name} ${i.value}\n"
            }
            return result
        }
    }
}