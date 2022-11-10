package com.example.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentInitialBinding
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InitialFragment : Fragment() {
    private lateinit var initialBinding: FragmentInitialBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initialBinding = FragmentInitialBinding.inflate(layoutInflater)
        return initialBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(initialBinding.root)
        loginViewModel.getDetails("Username")
        val token = loginViewModel.userToken

        lifecycleScope.launch(Dispatchers.Main) {
            token.collect() {
                if (navController.currentDestination?.id == R.id.initialFragment) {
                    if (it.isEmpty()) {
                        navController.navigate(InitialFragmentDirections.actionInitialFragmentToLoginFragment())
                    } else {
                        navController.navigate(InitialFragmentDirections.actionInitialFragmentToMainScreen())
                    }
                }
            }
        }
    }
}
