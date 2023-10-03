package com.jinglebroda.presentation.singleActivity.mvvm.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.FragmentMainBinding
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.navigator
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.main.viewModel.MainFragmentViewModel
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.SearchRecipeFragment
import com.jinglebroda.presentation.singleActivity.mvvm.utils.baseUiClasses.BaseFragment
import com.jinglebroda.presentation.singleActivity.mvvm.utils.factory.ViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.fragment_main) {
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    private val viewModel by viewModels<MainFragmentViewModel> { viewModelFactory }
    private lateinit var binding:FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        binding.openSearchScreenButton.setOnClickListener(this@MainFragment)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.openSearchScreenButton-> navigator().next(SearchRecipeFragment())
        }
    }
}