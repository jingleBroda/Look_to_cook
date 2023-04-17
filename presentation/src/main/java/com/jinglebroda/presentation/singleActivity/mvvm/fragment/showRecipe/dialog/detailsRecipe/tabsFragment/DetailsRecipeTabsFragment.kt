package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment

import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jinglebroda.domain.model.searchRecipeModel.Ingredient
import com.jinglebroda.domain.model.searchRecipeModel.NutrientInfo
import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString
import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeItemViewInfoConnector
import com.jinglebroda.presentation.BuildConfig
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.DetailsRecipeTabsFragmentBinding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment.copyTextHelper.DetailsRecipeCopyTextHelper
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment.listViewAdapter.TabsFragmentListViewAdapter

class DetailsRecipeTabsFragment <T: RecipeItemViewInfoConnector>(
    private val insideList:List<T>
): Fragment(R.layout.details_recipe_tabs_fragment){
    private lateinit var binding: DetailsRecipeTabsFragmentBinding
    private val textConverter = DetailsRecipeCopyTextHelper.Base()
    private lateinit var adapter:TabsFragmentListViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DetailsRecipeTabsFragmentBinding.bind(view)
        with(binding){
            val adapterList = mutableListOf<RecipeDoubleString>()
            insideList.forEach { i->
                adapterList.add(i.getBriefInformation())
            }
            adapter = TabsFragmentListViewAdapter(
                requireContext(),
                adapterList
            )
            detailsRecipeListView.adapter = adapter

            translateButton.setOnClickListener{
                //копируем содержимое таблицы в буффер
                val clipboardManager = requireContext().getSystemService(CLIPBOARD_SERVICE)
                        as ClipboardManager
                val clipData = ClipData.newPlainText(
                    "translateText",
                    textConverter.convertDataToString(adapter.getAdapterData())
                )
                clipboardManager.setPrimaryClip(clipData)
                //открываем google переводчик
                //TODO error: android.os.TransactionTooLargeException: data parcel size 869264 bytes
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(requireContext(), Uri.parse(BuildConfig.TRANSLATE_URL))
                Toast.makeText(
                    requireContext(),
                    requireContext().getText(R.string.copy_text_string),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        fun createDetailsIngredientList(insideList:List<Ingredient>):DetailsRecipeTabsFragment<Ingredient> =
            DetailsRecipeTabsFragment(insideList)

        fun createDetailsNutrientInfoList(insideList:List<NutrientInfo>):DetailsRecipeTabsFragment<NutrientInfo> =
            DetailsRecipeTabsFragment(insideList)
    }
}

/*
val builder = CustomTabsIntent.Builder()
val colorSchemeParams = CustomTabColorSchemeParams.Builder()
    .setToolbarColor(ContextCompat.getColor(v.context, R.color.white))
    .build()
builder.setDefaultColorSchemeParams(colorSchemeParams)
val customTabsIntent = builder.build()
customTabsIntent.launchUrl(v.context, Uri.parse(v.tag.toString()))
*/