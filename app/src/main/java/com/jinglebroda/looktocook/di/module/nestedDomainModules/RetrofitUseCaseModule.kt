package com.jinglebroda.looktocook.di.module.nestedDomainModules

import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.usecase.ApiSearchUseCase
import com.jinglebroda.domain.usecase.TranslateWordUseCase
import dagger.Module
import dagger.Provides

@Module
class RetrofitUseCaseModule {
    @Provides
    fun providesApiSearchUseCase(repository: DomainRepository) = ApiSearchUseCase(repository)

    @Provides
    fun providesTranslateWordUseCase(repository: DomainRepository) = TranslateWordUseCase(repository)
}