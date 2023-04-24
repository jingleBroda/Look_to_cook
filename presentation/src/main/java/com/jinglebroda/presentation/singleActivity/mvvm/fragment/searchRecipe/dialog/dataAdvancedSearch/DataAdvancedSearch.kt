package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch

data class DataAdvancedSearch(
    val caloriesFirstLimit:Int?,
    val caloriesLastLimit:Int?,
    val diet:List<String>?
){
    fun getRangeCalories():String? =
        if(
            (caloriesFirstLimit == null)||
            (caloriesLastLimit == null)||
            (caloriesLastLimit<caloriesFirstLimit)||
            (caloriesFirstLimit == 0)
        ){
            null
        }
        else{
            "$caloriesFirstLimit-$caloriesLastLimit"
        }
}