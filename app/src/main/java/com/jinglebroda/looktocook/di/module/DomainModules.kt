package com.jinglebroda.looktocook.di.module

import com.jinglebroda.looktocook.di.module.nestedDomainModules.RetrofitUseCaseModule
import dagger.Module

@Module(
    includes = [
        RetrofitUseCaseModule::class,
    ]
)
class DomainModules