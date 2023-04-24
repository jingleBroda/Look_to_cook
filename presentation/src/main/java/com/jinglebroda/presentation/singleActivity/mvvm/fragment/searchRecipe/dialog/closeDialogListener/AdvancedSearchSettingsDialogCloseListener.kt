package com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.closeDialogListener

import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.dialog.dataAdvancedSearch.DataAdvancedSearch

interface AdvancedSearchSettingsDialogCloseListener {
    fun closeDialog(selectedData: DataAdvancedSearch)
}