package com.example.instagramclone

import android.os.Bundle
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

        window.setStatusBarColor(SurfaceColors.SURFACE_5.getColor(this))

        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
