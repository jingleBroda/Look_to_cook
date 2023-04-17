package com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector

//класс содержит пару строк. Пара представляет собой имя-значение. Значение храниться в строке, потому что при таком подходе, не нужно заморачиваться на этапе создания объекта, какой тип значения прокидывать (просто кастим к string).
data class RecipeDoubleString(
    val name:String,
    val value:String
)