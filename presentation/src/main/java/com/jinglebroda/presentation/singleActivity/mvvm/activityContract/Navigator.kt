package com.jinglebroda.presentation.singleActivity.mvvm.activityContract

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.main.MainFragment
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.searchRecipe.SearchRecipeFragment
import com.jinglebroda.presentation.singleActivity.mvvm.fragment.showRecipe.ShowRecipeFragment

interface Navigator {
    fun next(fragment:Fragment, arg: Bundle? = null)
    fun nextNotAddToBackstack(
        destinationFragmentOneInFragmentTwoId:Int?,
        destinationFragmentOne:Int,
        arg:Bundle? = null
    )
    fun back()
}

fun Fragment.navigator() = requireActivity() as Navigator

fun Fragment.destinationId():Int? =
    when(this.javaClass) {
        MainFragment::class.java -> R.id.mainFragment
        SearchRecipeFragment::class.java -> R.id.searchRecipeFragment
        ShowRecipeFragment::class.java -> R.id.showRecipeFragment
        else -> null
    }

fun NavController.launchDestination(
    destinationId:Int?,
    arg:Bundle? = null
){
    navigate(
        destinationId ?: error("actionId in fragment = null"),
        arg,
        navOptions {
            anim {
                enter = androidx.appcompat.R.anim.abc_tooltip_enter
                exit = androidx.appcompat.R.anim.abc_tooltip_exit
                popEnter = androidx.appcompat.R.anim.abc_popup_enter
                popExit = androidx.appcompat.R.anim.abc_popup_exit
            }
        }
    )
}

fun NavController.launchDestinationNotAddToBackStack(
    destinationFragmentOneInFragmentTwoId:Int?,
    destinationFragmentTwo:Int,
    arg:Bundle? = null
){
    navigate(
        destinationFragmentOneInFragmentTwoId ?: error("actionId in fragment = null"),
        arg,
        navOptions {
            popUpTo(destinationFragmentTwo){
                inclusive = true
            }
            anim {
                enter = androidx.appcompat.R.anim.abc_tooltip_enter
                exit = androidx.appcompat.R.anim.abc_tooltip_exit
                popEnter = androidx.appcompat.R.anim.abc_popup_enter
                popExit = androidx.appcompat.R.anim.abc_popup_exit
            }
        }
    )
}