package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.presentation.databinding.RecipeItemViewV2Binding
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.diffUtils.RecipeDiffUtils
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.viewHolder.RecipeViewHolder

//пока без базавого класса
class RecipeRecViewAdapter(
    private val fragmentOnClickListener:View.OnClickListener
):RecyclerView.Adapter<RecipeViewHolder>(){
    private var data: Hits = Hits.generateEmptyHits()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  RecipeViewHolder(
            RecipeItemViewV2Binding.inflate(inflater),
            fragmentOnClickListener
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(data.hits[position].recipe)
    }

    override fun getItemCount(): Int = data.hits.size

    fun updateListItemView(newList: Hits) {
        val diffUtil = RecipeDiffUtils(data.hits, newList.hits)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        data = newList
        diffResults.dispatchUpdatesTo(this)
    }
}