package com.jinglebroda.looktocook.di.module

import com.jinglebroda.looktocook.di.module.nestedPresentationModules.FragmentModule
import com.jinglebroda.looktocook.di.module.nestedPresentationModules.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        FragmentModule::class
    ]
)
class PresentationModules {
}