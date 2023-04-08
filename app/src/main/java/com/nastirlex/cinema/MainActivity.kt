package com.nastirlex.cinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.nastirlex.cinema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.myToolbar)

        //val navView: BottomNavigationView = binding.navView

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_fragment_nav_host) as NavHostFragment
        //val navController = navHostFragment.navController

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_fragment_nav_host) as NavHostFragment
        //val navController = navHostFragment.navController
        //val navController = this.findNavController(R.id.activity_main_fragment_nav_host)

        //val navGraph = navController.navInflater.inflate(R.navigation.app_nav_graph)

        //navController.graph = navGraph

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
        //setupNavigation()


    }

//    private fun setupNavigation() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        when {
//            UserData.isAuthorized -> {
//                navGraph.setStartDestination(R.id.mainFlowFragment)
//            }
//            !UserData.isAuthorized -> {
//                navGraph.setStartDestination(R.id.signFlowFragment)
//            }
//        }
//        navController.graph = navGraph
//    }
}
