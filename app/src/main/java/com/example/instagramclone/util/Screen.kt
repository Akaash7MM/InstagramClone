package com.example.instagramclone.util

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Message : Screen("message_screen")
    object Profile : Screen("profile_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
}
