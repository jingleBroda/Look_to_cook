package com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams

data class AdvancedSearchGetParams(
    val ingredientsString:String,
    val caloriesRange:String?,
    val diet:List<String>?
)