package com.jinglebroda.data.retrofit2.searchRecipe

import com.jinglebroda.domain.model.searchRecipeModel.Hits
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRecipeService {
    @GET("https://api.edamam.com/search")
    fun searchRecipeQ(
        @Query("q") nameRecipe:String,
        @Query("from") from:Int = 0,
        @Query("to") to:Int = 20,
    ):Deferred<Hits>

}