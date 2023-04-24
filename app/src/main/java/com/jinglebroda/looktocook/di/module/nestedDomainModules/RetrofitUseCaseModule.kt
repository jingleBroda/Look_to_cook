package com.jinglebroda.looktocook.di.module.nestedDomainModules

import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.usecase.ApiDefaultSearchUseCase
import com.jinglebroda.domain.usecase.TranslateWordUseCase
import dagger.Module
import dagger.Provides

@Module
class RetrofitUseCaseModule {
    @Provides
    fun providesApiDefaultSearchUseCase(repository: DomainRepository) =
        ApiDefaultSearchUseCase(repository)

    @Provides
    fun providesTranslateWordUseCase(repository: DomainRepository) =
        TranslateWordUseCase(repository)
}