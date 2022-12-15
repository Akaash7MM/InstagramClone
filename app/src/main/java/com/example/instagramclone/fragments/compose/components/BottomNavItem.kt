package com.example.instagramclone.fragments.compose.components

import androidx.compose.runtime.Immutable
import com.example.instagramclone.R
import com.example.instagramclone.util.Screen

@Immutable
data class BottomNavItem(
    val route: String,
    val unselectedIcon: Int,
    val selectedIcon: Int

)
object BottomNavList {
    val list = listOf(
        BottomNavItem(
            route = Screen.Home.route,
            unselectedIcon = R.drawable.ic_home_false,
            selectedIcon = R.drawable.ic_home_true
        ),

        BottomNavItem(
            route = "",
            unselectedIcon = R.drawable.ic_mag_false,
            selectedIcon = R.drawable.ic_mag_true
        ),
        BottomNavItem(
            route = "",
            unselectedIcon = R.drawable.ic_add_post_false,
            selectedIcon = R.drawable.ic_add_post_true
        ),
        BottomNavItem(
            route = "",
            unselectedIcon = R.drawable.ic_heart_512_false,
            selectedIcon = R.drawable.ic_heart_512_true
        ),
        BottomNavItem(
            route = Screen.Profile.route,
            unselectedIcon = R.drawable.ic_profile,
            selectedIcon = R.drawable.ic_profile
        )
    )
}
