package com.jinglebroda.looktocook.di.module.nestedDataModules

import com.jinglebroda.data.DataRepository
import com.jinglebroda.data.retrofit2.searchRecipe.SearchRecipeService
import com.jinglebroda.data.retrofit2.translateWord.TranslateWordService
import com.jinglebroda.domain.DomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun createRepository(
        searchRecipeService: SearchRecipeService,
        translateWordService: TranslateWordService
    ):DomainRepository =
        DataRepository(searchRecipeService, translateWordService)
}