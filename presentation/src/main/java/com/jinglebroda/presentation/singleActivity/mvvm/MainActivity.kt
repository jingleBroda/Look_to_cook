package com.jinglebroda.presentation.singleActivity.mvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.ActivityMainBinding
import com.jinglebroda.presentation.singleActivity.mvvm.activityContract.*

class MainActivity : AppCompatActivity(), Navigator, InternetConnection {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController:NavController
    private lateinit var networkConnectivityChecker:NetworkConnectivityChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(
            R.id.FragmentLayout
        ) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this,navController)
        networkConnectivityChecker = NetworkConnectivityChecker(this)
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

    override fun isInternetConnected(): Boolean = networkConnectivityChecker.isInternetConnected()

    override fun onStart() {
        networkConnectivityChecker.startListening()
        super.onStart()
    }

    override fun onStop() {
        networkConnectivityChecker.stopListening()
        super.onStop()
    }

}