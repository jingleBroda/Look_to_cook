package com.jinglebroda.looktocook.di.module.nestedPresentationModules

import com.jinglebroda.presentation.singleActivity.mvvm.fragment.main.MainFragment
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.SearchRecipeFragment
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.ShowRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun providesMainFragment():MainFragment

    @ContributesAndroidInjector
    abstract fun providesSearchRecipeFragment():SearchRecipeFragment

    @ContributesAndroidInjector
    abstract fun providesShowRecipeFragment():ShowRecipeFragment
}