package com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.handlers

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface SearchRecipesHandler{
    fun clearRecipe()
    fun searchRecipe()
    fun showRecipe()

    class Base(
        private val emptyRecipeWarning: TextView,
        private val progressBar: ProgressBar,
        private val recipeRecycler:RecyclerView,
    ):SearchRecipesHandler{
        override fun clearRecipe() {
            emptyRecipeWarning.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            recipeRecycler.visibility = View.GONE
        }

        override fun searchRecipe() {
            emptyRecipeWarning.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recipeRecycler.visibility = View.GONE
        }

        override fun showRecipe() {
            emptyRecipeWarning.visibility = View.GONE
            progressBar.visibility = View.GONE
            recipeRecycler.visibility = View.VISIBLE
        }

    }
}