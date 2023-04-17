package com.jinglebroda.presentation.singleActivity.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.ActivityMainBinding
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.Navigator
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.destinationId
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.launchDestination
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.launchDestinationNotAddToBackStack

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(
            R.id.FragmentLayout
        ) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this,navController)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun next(fragment: Fragment, arg: Bundle?) {
        navController.launchDestination(fragment.destinationId(), arg)
    }

    override fun nextNotAddToBackstack(
        destinationFragmentOneInFragmentTwoId: Int?,
        destinationFragmentOne: Int,
        arg: Bundle?
    ) {
        navController.launchDestinationNotAddToBackStack(
            destinationFragmentOneInFragmentTwoId,
            destinationFragmentOne,
            arg
        )
    }

    override fun back() = onBackPressed()
}