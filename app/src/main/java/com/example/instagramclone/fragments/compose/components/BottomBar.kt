package com.example.instagramclone.fragments.compose.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    val selectedItemRoute = navHostController.currentBackStackEntryAsState()
    val items = BottomNavList.list

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            val selected = item.route == selectedItemRoute.value?.destination?.route

            BottomNavigationItem(
                icon = {
                    if (selected) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.selectedIcon),
                            contentDescription = ""
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.unselectedIcon),
                            contentDescription = ""
                        )
                    }
                },
                selected = selected,
                onClick = { navHostController.navigate(item.route) }
            )
        }
    }
}
