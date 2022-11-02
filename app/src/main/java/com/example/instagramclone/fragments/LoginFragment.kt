package com.example.instagramclone.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.example.instagramclone.R
import com.example.instagramclone.databinding.LoginActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var loginActivityBinding: LoginActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginActivityBinding = LoginActivityBinding.inflate(layoutInflater)
        return loginActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Log.d("LoginActivity", "Sign up")
            }
        }

        val string = SpannableString(loginActivityBinding.tvSignUp.text)
        string.setSpan(
            clickableSpan,
            loginActivityBinding.tvSignUp.text.length - 7,
            loginActivityBinding.tvSignUp.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        loginActivityBinding.tvSignUp.text = string
        loginActivityBinding.tvSignUp.setOnClickListener() {
        }

        loginActivityBinding.BtLogin.setOnClickListener() {
            Navigation.findNavController(loginActivityBinding.root)
                .navigate(R.id.action_loginFragment_to_LoggedIn)
        }

//        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//        Navigation.findNavController(loginActivityBinding.root)
//            .addOnDestinationChangedListener { _, nd: NavDestination, _ ->
//                if (nd.id == R.id.loginFragment) {
//                    bottomNavigationView.visibility = View.GONE
//                } else {
//                    bottomNavigationView.visibility = View.VISIBLE
//                }
//            }


    }
}
