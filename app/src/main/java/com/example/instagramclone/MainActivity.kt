package com.example.instagramclone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.fragments.Navigation
import com.example.instagramclone.fragments.ui.theme.InstagramCloneTheme
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = SurfaceColors.SURFACE_5.getColor(this)

        setContent {
            InstagramCloneTheme() {
                navController = rememberNavController()
                Navigation(navController = navController)
            }
        }

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
//        val navController = navHostFragment.navController
//        binding.bottomNavigationView.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.loginFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                }
//                R.id.initialFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                }
//                R.id.signupFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                }
//                R.id.messageFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                }
//                else -> {
//                    binding.bottomNavigationView.visibility = View.VISIBLE
//                }
//            }
//        }
    }
}
