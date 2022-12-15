package com.example.instagramclone.fragments.compose.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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
    val items = remember { BottomNavList.list }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            val selected = remember {
                derivedStateOf { item.route == selectedItemRoute.value?.destination?.route }
            }

            BottomNavigationItem(
                icon = {
                    if (selected.value) {
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
                selected = selected.value,
                onClick = { navHostController.navigate(item.route) }
            )
        }
    }
}
