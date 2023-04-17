package com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize

import android.os.Parcelable
import com.jinglebroda.domain.model.searchRecipeModel.Ingredient
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientParcelize(
    val foodId:String,
    val quantity:Float,
    val measure:String?, //Measure?,
    val weight:Float,
    val food: String?, //Food,
    val foodCategory:String?,
): Parcelable{
    fun getIngredient(): Ingredient = Ingredient(
        foodId,
        quantity,
        measure,
        weight,
        food,
        foodCategory
    )

    companion object{
        fun createIngredientParcelize(ingredient: Ingredient):IngredientParcelize{
            with(ingredient) {
                return IngredientParcelize(
                    foodId,
                    quantity,
                    measure,
                    weight,
                    food,
                    foodCategory
                )
            }
        }

        fun createIngredientList(parcelList:List<IngredientParcelize>):List<Ingredient>{
            val result = mutableListOf<Ingredient>()
            parcelList.forEach { element->
                result.add(element.getIngredient())
            }
            return result
        }

        fun createIngredientParcelizeList(simpleList:List<Ingredient>):List<IngredientParcelize>{
            val result = mutableListOf<IngredientParcelize>()
            simpleList.forEach { element->
                result.add(createIngredientParcelize(element))
            }
            return result
        }
    }
}