package com.jinglebroda.presentation.singleActivity.mvvm.utils.recipeParcelize

import android.os.Parcelable
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import kotlinx.parcelize.Parcelize

@Parcelize
data class HitsParcelize(
    val q:String,
    val from:Int,
    val to:Int,
    val count:Int,
    val more:Boolean,
    val hits:List<HitParcelize>
) : Parcelable{
    fun getHits():Hits = Hits(
        q,
        from,
        to,
        count,
        more,
        HitParcelize.createHitList(hits)
    )

    companion object{
        fun createHitsParcelize(hits:Hits):HitsParcelize{
            with(hits){
                return HitsParcelize(
                    q,
                    from,
                    to,
                    count,
                    more,
                    HitParcelize.createHitListParcelize(hits.hits)
                )
            }
        }
    }
}