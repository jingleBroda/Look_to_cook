package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.handlers

import android.view.View
import android.widget.Button
import android.widget.ProgressBar

abstract class SearchProcessingHandler {
    abstract fun showButton()
    abstract fun hideButton()

    class Base(
        private val progressBar:ProgressBar,
        private val searchButton: Button
    ): SearchProcessingHandler(){
        override fun showButton(){
            progressBar.visibility = View.INVISIBLE
            searchButton.visibility = View.VISIBLE
        }

        override fun hideButton(){
            progressBar.visibility = View.VISIBLE
            searchButton.visibility = View.INVISIBLE
        }
    }
}