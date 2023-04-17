package com.jinglebroda.domain.model.searchRecipeModel

import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString
import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeItemViewInfoConnector

data class Ingredient(
    val foodId:String,
    val quantity:Float,
    val measure:String?, //Measure?,
    val weight:Float,
    val food: String?, //Food,
    val foodCategory:String?,
): RecipeItemViewInfoConnector {
    override fun getBriefInformation(): RecipeDoubleString =
        RecipeDoubleString(
            food?:"",
            "$quantity $measure"
        )
}