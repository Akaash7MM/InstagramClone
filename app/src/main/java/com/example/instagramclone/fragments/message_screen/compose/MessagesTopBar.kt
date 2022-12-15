package com.example.instagramclone.fragments.message_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.R.drawable
import com.example.instagramclone.R.string

@Composable
fun MessagesTopBar(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = string.username_text), fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = drawable.ic_arrow_left),
                    contentDescription = stringResource(id = R.string.left_arrow)
                )
            }
        },
        actions = {
            MenuItems()
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 1.dp
    )
}

@Composable
fun MenuItems() {
    Icon(
        modifier = Modifier
            .size(22.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { },
        painter = painterResource(id = drawable.ic_video_call),
        contentDescription = stringResource(id = R.string.video_call)
    )
    Spacer(modifier = Modifier.width(24.dp))
    Icon(
        modifier = Modifier
            .size(22.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { },
        painter = painterResource(id = drawable.ic_plus_64),
        contentDescription = stringResource(id = R.string.new_message)
    )
    Spacer(modifier = Modifier.width(8.dp))
}
