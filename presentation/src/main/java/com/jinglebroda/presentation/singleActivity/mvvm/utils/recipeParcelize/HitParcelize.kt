package com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize

import android.os.Parcelable
import com.jinglebroda.domain.model.searchRecipeModel.Hit
import kotlinx.parcelize.Parcelize

@Parcelize
data class HitParcelize(
    val recipe: RecipeParcelize,
    val bookmarked:Boolean,
    val bought:Boolean
): Parcelable {
    fun getHit(): Hit = Hit(
        recipe.getRecipe(),
        bookmarked,
        bought
    )

    companion object{
        fun createHitParcelize(hit:Hit):HitParcelize{
            with(hit){
                return HitParcelize(
                    RecipeParcelize.createRecipeParcel(recipe),
                    bookmarked,
                    bought
                )
            }
        }

        fun createHitListParcelize(hitList:List<Hit>):List<HitParcelize>{
            val result = mutableListOf<HitParcelize>()
            hitList.forEach { element->
                result.add(createHitParcelize(element))
            }
            return result
        }

        fun createHitList(hitListParcelize:List<HitParcelize>):List<Hit>{
            val result = mutableListOf<Hit>()
            hitListParcelize.forEach { element->
                result.add(element.getHit())
            }
            return result
        }
    }
}