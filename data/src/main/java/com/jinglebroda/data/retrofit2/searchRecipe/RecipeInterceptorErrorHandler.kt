package com.jinglebroda.data.retrofit2.searchRecipe

import android.content.Context
import java.io.IOException

interface RecipeInterceptorErrorHandler {
    fun isConnected(context: Context):Boolean
    class NoConnectivityException(message: String) : IOException(message)
    class ServerException(message: String) : IOException(message)
}