package com.jinglebroda.data.retrofit2.searchRecipe

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class SearchRecipeInterceptor(private val appID:String, private val appKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val url = originalRequest.url().newBuilder()
            .addQueryParameter("app_id", appID)
            .addQueryParameter("app_key", appKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}