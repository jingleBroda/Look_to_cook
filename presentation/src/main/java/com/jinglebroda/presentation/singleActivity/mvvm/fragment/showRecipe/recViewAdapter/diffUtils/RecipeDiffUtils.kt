package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.recViewAdapter.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.jinglebroda.domain.model.searchRecipeModel.Hit

class RecipeDiffUtils(
    private val oldList:List<Hit>,
    private val newList:List<Hit>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].recipe == newList[newItemPosition].recipe

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = when {
        oldList[oldItemPosition].recipe != newList[newItemPosition].recipe ->{
            false
        }

        oldList[oldItemPosition].bookmarked != newList[newItemPosition].bookmarked ->{
            false
        }

        oldList[oldItemPosition].bought != newList[newItemPosition].bought ->{
            false
        }

        else -> true
    }
}