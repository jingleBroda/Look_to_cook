package com.jinglebroda.data.retrofit2.translateWord

import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateWordService {
    @GET("https://api.mymemory.translated.net/get")
    fun translate(
        @Query("q") word:String,
        @Query("langpair") langpair:String
    ):Deferred<TranslateWordResponse>
}