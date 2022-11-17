package com.example.instagramclone.fragments.login_screen // ktlint-disable package-name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.instagramclone.R
import com.example.instagramclone.databinding.LoginActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var loginActivityBinding: LoginActivityBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginActivityBinding = LoginActivityBinding.inflate(layoutInflater)
        return loginActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var usernameText = false
        var passText = false
        val navController = Navigation.findNavController(loginActivityBinding.root)

        loginActivityBinding.apply {
            EtUsername.doOnTextChanged() { text, start, before, count ->
                usernameText = !text.isNullOrBlank()
                BtLogin.isEnabled = usernameText && passText
            }
            EtPassword.doOnTextChanged() { text, start, before, count ->
                passText = !text.isNullOrBlank()
                BtLogin.isEnabled = usernameText && passText
            }
        }
        loginActivityBinding.tvSignUp.setOnClickListener() {
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        loginActivityBinding.BtLogin.setOnClickListener() {
            val email = loginActivityBinding.EtUsername.text.toString()
            val password = loginActivityBinding.EtPassword.text.toString()
            loginViewModel.loginUser(email, password)
        }
        lifecycleScope.launch {
            loginViewModel.userLoggedIn.collect { isLoggedin ->
                if (isLoggedin) {
                    navController.popBackStack()
                }
            }
        }
    }
}
