package com.jinglebroda.domain.model.searchRecipeModel

data class Recipe(
    val uri:String,
    val label:String,
    val image:String,
    val source:String,
    val url:String,
    val yield:Float,
    val calories:Float,
    val totalWeight:Float,
    val ingredients:List<Ingredient>,
    val totalNutrients:Map<String, NutrientInfo>,
    val totalDaily:Map<String, NutrientInfo>,
    val dietLabels:List<String>,//List<DietLabels>, //в документации, написан тип enum[]
    val healthLabels:List<String>,//List<HealthLabels> //в документации, написан тип enum[]
)