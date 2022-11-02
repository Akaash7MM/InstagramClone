package com.example.instagramclone

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.instagramclone.databinding.ActivityMainBinding
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = SurfaceColors.SURFACE_5.getColor(this)
        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.END))
            when (destination.id) {
                R.id.mainScreen -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }

//        binding.bottomNavigationView.visibility = View.GONE

//        supportFragmentManager.registerFragmentLifecycleCallbacks(
//            object : FragmentManager.FragmentLifecycleCallbacks() {
//                override fun onFragmentCreated(
//                    fm: FragmentManager,
//                    f: Fragment,
//                    savedInstanceState: Bundle?
//                ) {
//                    TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.END))
//                    when (f) {
//                        is LoginFragment -> {
//                        }
//                        is SearchScreen -> {
//                            binding.bottomNavigationView.visibility = View.GONE
//                        }
//                        else -> {
//                            binding.bottomNavigationView.visibility = View.VISIBLE
//                        }
//                    }
//                }
//            },
//            true
//        )
    }
}
