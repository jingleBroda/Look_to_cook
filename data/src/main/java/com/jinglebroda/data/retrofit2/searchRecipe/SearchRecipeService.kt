package com.jinglebroda.data.retrofit2.searchRecipe

import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.test.DietLabels
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface SearchRecipeService {
    @GET("https://api.edamam.com/search")
    fun searchRecipe(
        @Query("q") nameRecipe:String,
        @Query("calories") caloriesString:String? = null,
        @Query("diet") dietString: Array<String>? = null,
        @Query("from") from:Int = 0,
        @Query("to") to:Int = 20,
    ):Deferred<Hits>
}