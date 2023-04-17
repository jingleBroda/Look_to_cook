package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.dialog.detailsRecipe.tabsFragment.listViewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jinglebroda.domain.model.searchRecipeModel.recipeInfoConnector.RecipeDoubleString
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.DoubleStringLayoutBinding
import com.jinglebroda.presentation.databinding.IngredientsItemViewBinding

class TabsFragmentListViewAdapter(
    context: Context,
    private val items: List<RecipeDoubleString>
):ArrayAdapter<RecipeDoubleString>(context, 0, items) {
    private lateinit var binding: DoubleStringLayoutBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null)
            view = LayoutInflater.from(context).inflate(
                R.layout.double_string_layout,
                parent,
                false
            )
        binding = DoubleStringLayoutBinding.bind(view!!)

        with(binding){
            nameInDoubleString.text = items[position].name
            valueInDoubleString.text = items[position].value
        }

        return binding.root
    }

    fun getAdapterData():List<RecipeDoubleString> = items
}