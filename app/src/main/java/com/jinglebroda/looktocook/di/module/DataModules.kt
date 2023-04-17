package com.jinglebroda.looktocook.di.module

import com.jinglebroda.looktocook.di.module.nestedDataModules.RepositoryModule
import com.jinglebroda.looktocook.di.module.nestedDataModules.RetrofitModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        RepositoryModule::class,
    ]
)
class DataModules