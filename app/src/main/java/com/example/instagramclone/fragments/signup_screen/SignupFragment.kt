package com.example.instagramclone.fragments.signup_screen // ktlint-disable package-name

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.instagramclone.R
import com.example.instagramclone.databinding.SignupActivityBinding
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignupFragment : Fragment() {

    lateinit var signupActivityBinding: SignupActivityBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupActivityBinding = SignupActivityBinding.inflate(layoutInflater)
        return signupActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(signupActivityBinding.root)
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editText: Editable?) {
                signupActivityBinding.apply {
                    when (editText) {
                        EtEmail -> BtSignUp.isEnabled = editText.isBlank()
                        EtFullName -> BtSignUp.isEnabled = editText.isBlank()
                        EtUsername -> BtSignUp.isEnabled = editText.isBlank()
                        EtPassword -> BtSignUp.isEnabled = editText.isBlank()
                        else -> BtSignUp.isEnabled = true
                    }
                }
            }
        }
        firebaseAuth = FirebaseAuth.getInstance()
        signupActivityBinding.BtSignUp.setOnClickListener() {
            val email = signupActivityBinding.EtEmail.text.toString()
            val password = signupActivityBinding.EtPassword.text.toString()
            loginViewModel.createUser(email, password)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            loginViewModel.userCreated.collect() {
                if (it) {
                    withContext(Dispatchers.Main) {
                        navController.navigate(R.id.action_signupFragment_to_mainScreen)
                    }
                }
            }
        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            loginViewModel.currentAuthFlow().collect { userNotNull ->
//                if (userNotNull) { Navigation.findNavController(signupActivityBinding.root).popBackStack()
//                }
//            }
//        }

        signupActivityBinding.EtEmail.addTextChangedListener(textWatcher)

        signupActivityBinding.apply {
            EtEmail.addTextChangedListener(textWatcher)
            EtFullName.addTextChangedListener(textWatcher)
            EtUsername.addTextChangedListener(textWatcher)
            EtPassword.addTextChangedListener(textWatcher)
        }
    }
}

//        loginActivityBinding.BtLogin.setOnClickListener() {
//            val email = loginActivityBinding.EtUsername.text.toString()
//            val password = loginActivityBinding.EtPassword.text.toString()
//            loginViewModel.createUser(email, password)
//
//            loginViewModel.saveDetails("Username", password)
//            loginViewModel.getDetails("Username")
//        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            loginViewModel.abc().collect { isLoggedIn ->
//            }
//        }
//        lifecycleScope.launch(Dispatchers.Main) {
//            data.collect() {
//
//            }
//        }

//    private fun isEtFilled(): Boolean {
//
//    }
//    data class isEtFilledBoolean(val username: Boolean = false, val password: Boolean = false, val email: Boolean = false, val name: Boolean = false)
