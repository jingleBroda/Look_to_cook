package com.jinglebroda.looktocook.di.module.nestedDataModules


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jinglebroda.data.retrofit2.searchRecipe.SearchRecipeInterceptor
import com.jinglebroda.data.retrofit2.searchRecipe.SearchRecipeService
import dagger.Module
import dagger.Provides
import looktocook.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class SearchRecipeModule {
    private val baseUrl = "https://api.edamam.com"

    @Provides
    fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(SearchRecipeInterceptor(BuildConfig.APP_ID, BuildConfig.APP_KEY))
        .build()


    @Provides
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()

    @Provides
    fun createRetrofitService(retrofit: Retrofit): SearchRecipeService =
        retrofit.create(SearchRecipeService::class.java)
}