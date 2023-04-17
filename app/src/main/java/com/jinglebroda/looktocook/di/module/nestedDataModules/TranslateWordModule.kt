package com.jinglebroda.looktocook.di.module.nestedDataModules

import com.jinglebroda.data.retrofit2.translateWord.TranslateWordService
import com.jinglebroda.looktocook.di.module.nestedDataModules.retrofitCreateHelper.TranslateRetrofitCreator
import dagger.Module
import dagger.Provides

@Module
class TranslateWordModule {
    private val baseUrl = "https://api.mymemory.translated.net"

    @Provides
    fun createTranslateRetrofit(): TranslateRetrofitCreator = TranslateRetrofitCreator()

    @Provides
    fun createTranslateRetrofitService(translateRetrofitCreator: TranslateRetrofitCreator): TranslateWordService =
        translateRetrofitCreator.create(baseUrl).create(TranslateWordService::class.java)
}