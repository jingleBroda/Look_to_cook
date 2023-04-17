package com.jinglebroda.looktocook.di.module.nestedDataModules

import dagger.Module

@Module(
    includes = [
        SearchRecipeModule::class,
        TranslateWordModule::class
    ]
)
class RetrofitModule