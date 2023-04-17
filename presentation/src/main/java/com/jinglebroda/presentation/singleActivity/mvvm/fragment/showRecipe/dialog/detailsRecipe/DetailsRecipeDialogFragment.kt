package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.jinglebroda.domain.model.searchRecipeModel.Ingredient
import com.jinglebroda.domain.model.searchRecipeModel.NutrientInfo
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.DetailsRecipeDialogFragmentBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsAdapter.DetailsRecipeTabsAdapter
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment.DetailsRecipeTabsFragment

class DetailsRecipeDialogFragment(
    private val ingredientList:List<Ingredient>,
    private val nutrientsMap:Map<String,NutrientInfo>
):DialogFragment(R.layout.details_recipe_dialog_fragment) {
    private lateinit var binding:DetailsRecipeDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsRecipeDialogFragmentBinding.inflate(inflater)
        with(binding){
            // формируем список nutrientsList
            val nutrientsList = mutableListOf<NutrientInfo>()
            nutrientsMap.forEach { (key, value)->
                nutrientsList.add(value)
            }
            // Создание списка фрагментов для каждой вкладки
            val fragmentList = listOf(
                DetailsRecipeTabsFragment.createDetailsIngredientList(ingredientList),
                DetailsRecipeTabsFragment.createDetailsNutrientInfoList(nutrientsList)
            )
            // Создание списка заголовков для каждой вкладки
            val titleList = listOf(
                resources.getString(R.string.ingredient_string),
                resources.getString(R.string.nutrients_string)
            )
            val adapter = DetailsRecipeTabsAdapter(childFragmentManager, fragmentList, titleList)
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
            tabLayout.setSelectedTabIndicatorColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.backgroundButton
                )
            )
        }
        return binding.root
    }

}