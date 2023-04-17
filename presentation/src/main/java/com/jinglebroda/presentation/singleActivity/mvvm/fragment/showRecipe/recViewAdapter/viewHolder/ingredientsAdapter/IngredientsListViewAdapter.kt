package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.viewHolder.ingredientsAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jinglebroda.domain.model.searchRecipeModel.Ingredient
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.IngredientsItemViewBinding

class IngredientsListViewAdapter(
    context: Context,
    private val items: List<String>
):ArrayAdapter<String>(context, 0, items) {
    private lateinit var binding:IngredientsItemViewBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null)
            view = LayoutInflater.from(context).inflate(
                R.layout.ingredients_item_view,
                parent,
                false
            )
        binding = IngredientsItemViewBinding.bind(view!!)

        with(binding){
            nameIngredient.text = items[position]
        }

        return binding.root
    }
}