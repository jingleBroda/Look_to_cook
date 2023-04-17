package com.jinglebroda.domain.model.searchRecipeModel

data class Hit(
    val recipe: Recipe,
    val bookmarked:Boolean,
    val bought:Boolean
)