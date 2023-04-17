package com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize

import android.os.Parcelable
import com.jinglebroda.domain.model.searchRecipeModel.Recipe
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeParcelize(
    val uri:String,
    val label:String,
    val image:String,
    val source:String,
    val url:String,
    val yield:Float,
    val calories:Float,
    val totalWeight:Float,
    val ingredients:List<IngredientParcelize>,
    val totalNutrients:Map<String, NutrientInfoParcelize>,
    val totalDaily:Map<String, NutrientInfoParcelize>,
    val dietLabels:List<String>,//List<DietLabels>, //в документации, написан тип enum[]
    val healthLabels:List<String>,//List<HealthLabels> //в документации, написан тип enum[]
): Parcelable{
    fun getRecipe():Recipe = Recipe(
        uri,
        label,
        image,
        source,
        url,
        this.yield,
        calories,
        totalWeight,
        IngredientParcelize.createIngredientList(ingredients),
        NutrientInfoParcelize.createNutrientInfoMap(totalNutrients),
        NutrientInfoParcelize.createNutrientInfoMap(totalDaily),
        dietLabels,
        healthLabels
    )

    companion object{
        fun createRecipeParcel(recipe:Recipe):RecipeParcelize{
           with(recipe) {
               return RecipeParcelize(
                   uri,
                   label,
                   image,
                   source,
                   url,
                   recipe.yield,
                   calories,
                   totalWeight,
                   IngredientParcelize.createIngredientParcelizeList(ingredients),
                   NutrientInfoParcelize.createNutrientInfoParcelizeMap(totalNutrients),
                   NutrientInfoParcelize.createNutrientInfoParcelizeMap(totalDaily),
                   dietLabels,
                   healthLabels
               )
           }
        }
    }
}