package com.example.instagramclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.instagramclone.Fragments.MainScreen
import com.example.instagramclone.Fragments.SearchScreen
import com.example.instagramclone.databinding.ActivityMainBinding
import com.google.android.material.elevation.SurfaceColors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setStatusBarColor(SurfaceColors.SURFACE_5.getColor(this))

        val mainScreenFragment = MainScreen()
        val searchScreenFragment = SearchScreen()
//
//        setContentFragment(mainScreenFragment)
        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
        val navController = navHostFragment.navController
 //        val navController = binding.fragmentContainerView.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.mainScreen -> Navigation.findNavController(mainScreenFragment.requireView()).navigate(R.id.action_mainScreen_to_searchScreen2)
//                R.id.searchScreen -> Navigation.findNavController(searchScreenFragment.requireView()).navigate(R.id.action_searchScreen_to_mainScreen2)
//            }
//            true
//        }
    }

//    private fun setContentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment, fragment)
//            setCustomAnimations(
//                R.anim.fadeout,
//                R.anim.fadeout
//            )
//            commit()
//        }
//    }
}
