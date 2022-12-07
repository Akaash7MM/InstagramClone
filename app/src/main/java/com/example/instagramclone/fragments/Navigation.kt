package com.example.instagramclone.fragments

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.login_screen.compose.LoginScreenComposable
import com.example.instagramclone.fragments.main_screen.compose.MainScreenComposable
import com.example.instagramclone.fragments.message_screen.compose.MessagesComposable
import com.example.instagramclone.fragments.profile_screen.compose.ProfileScreenComposable
import com.example.instagramclone.fragments.signup_screen.compose.SignUpComposable
import com.example.instagramclone.util.Screen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val isLoggedin = loginViewModel.userAuthChangeFlow().collectAsStateWithLifecycle(initialValue = null)
    val startDestination = getDestination(isLoggedin.value)

    if (startDestination != null) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(Screen.SignUp.route) {
                SignUpComposable(navController = navController)
            }
            composable(Screen.Home.route) {
                MainScreenComposable(navController = navController)
            }
            composable(Screen.Message.route) {
                MessagesComposable(navController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreenComposable(navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreenComposable(navController = navController)
            }
            composable(Screen.SignUp.route) {
                SignUpComposable(navController = navController)
            }
        }
    }
}

fun getDestination(isLoggedin: Boolean?): String? {
    return if (isLoggedin == true) Screen.Home.route else if (isLoggedin == false) Screen.Login.route else null
}
