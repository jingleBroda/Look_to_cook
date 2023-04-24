package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.utils.handlers

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

abstract class SearchProcessingHandler {
    abstract fun showButton()
    abstract fun hideButton()

    class Base(
        private val progressBar:ProgressBar,
        private val searchButton: Button,
        private val openCustomizeSearch: TextView
    ): SearchProcessingHandler(){
        override fun showButton(){
            progressBar.visibility = View.INVISIBLE
            searchButton.visibility = View.VISIBLE
            openCustomizeSearch.visibility = View.VISIBLE
        }

        override fun hideButton(){
            progressBar.visibility = View.VISIBLE
            searchButton.visibility = View.INVISIBLE
            openCustomizeSearch.visibility = View.INVISIBLE
        }
    }
}