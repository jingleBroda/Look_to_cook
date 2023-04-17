package com.jinglebroda.looktocook.di.module.nestedPresentationModules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.main.viewModel.MainFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.viewModel.SearchRecipeFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.viewModel.ShowRecipeFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.utils.factory.ViewModelFactory
import com.jinglebroda.looktocook.di.module.nestedPresentationModules.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    internal abstract fun bindMainFragmentViewModel(
        mainMenuViewModel: MainFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowRecipeFragmentViewModel::class)
    internal abstract fun bindSecondFragmentViewModel(
        secondFragmentViewModel: ShowRecipeFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchRecipeFragmentViewModel::class)
    internal abstract fun bindSearchRecipeFragmentViewModel(
        searchRecipeFragmentViewModel: SearchRecipeFragmentViewModel
    ): ViewModel
}