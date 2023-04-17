package com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize

import android.os.Parcelable
import com.jinglebroda.domain.model.searchRecipeModel.NutrientInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientInfoParcelize(
    val label:String,
    val quantity:Float,
    val unit:String
) : Parcelable{
    fun getNutrientInfo(): NutrientInfo = NutrientInfo(
        label,
        quantity,
        unit
    )

    companion object{
        fun createNutrientInfoParcelize(nutrientInfo:NutrientInfo):NutrientInfoParcelize{
            with(nutrientInfo){
                return NutrientInfoParcelize(
                    label,
                    quantity,
                    unit
                )
            }
        }

        fun createNutrientInfoParcelizeMap(nutrientInfoMap:Map<String, NutrientInfo>):Map<String, NutrientInfoParcelize>{
            val result = mutableMapOf<String, NutrientInfoParcelize>()
            nutrientInfoMap.forEach { (keyString, valueNutrientInfo) ->
                result[keyString] = createNutrientInfoParcelize(valueNutrientInfo)
            }
            return result
        }

        fun createNutrientInfoMap(nutrientInfoParcelizeMap:Map<String, NutrientInfoParcelize>):Map<String, NutrientInfo>{
            val result = mutableMapOf<String, NutrientInfo>()
            nutrientInfoParcelizeMap.forEach { (keyString, valueNutrientInfoParcelize) ->
                result[keyString] = valueNutrientInfoParcelize.getNutrientInfo()
            }
            return result
        }
    }
}