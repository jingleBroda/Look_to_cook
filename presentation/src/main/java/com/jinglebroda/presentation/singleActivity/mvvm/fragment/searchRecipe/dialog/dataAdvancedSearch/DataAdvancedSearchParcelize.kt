package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataAdvancedSearchParcelize(
    val caloriesFirstLimit:Int?,
    val caloriesLastLimit:Int?,
    val diet:List<String>?
): Parcelable{
    fun getDataAdvancedSearch():DataAdvancedSearch = DataAdvancedSearch(
        caloriesFirstLimit,
        caloriesLastLimit,
        diet
    )

    companion object{
        fun createDataAdvancedSearchParcelize(
            advancedSearch:DataAdvancedSearch
        ):DataAdvancedSearchParcelize =
            DataAdvancedSearchParcelize(
                advancedSearch.caloriesFirstLimit,
                advancedSearch.caloriesLastLimit,
                advancedSearch.diet
            )
    }
}