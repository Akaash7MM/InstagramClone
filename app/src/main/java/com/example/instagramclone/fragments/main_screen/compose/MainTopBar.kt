package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R.drawable
import com.example.instagramclone.R.string

@Composable
fun MainTopBar(onMessagesClick: () -> Unit) {
    TopAppBar(
        title = {
            Icon(
                modifier = Modifier.size(108.dp),
                painter = painterResource(id = drawable.ic_instagram_logo),
                contentDescription = stringResource(id = string.instagram_logo)
            )
        },
        actions = {
            MenuItems(onMessagesClick = onMessagesClick)
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 1.dp
    )
}

@Composable
fun MenuItems(
    onMessagesClick: () -> Unit
) {
    Icon(
        modifier = Modifier.size(22.dp),
        painter = painterResource(id = drawable.ic_add_post_false),
        contentDescription = stringResource(id = string.addpost_logo)
    )
    Spacer(modifier = Modifier.width(24.dp))
    Icon(
        modifier = Modifier
            .size(22.dp)
            .clickable(onClick = onMessagesClick),
        painter = painterResource(id = drawable.ic_messenger),
        contentDescription = stringResource(id = string.messenger_logo)
    )
    Spacer(modifier = Modifier.width(8.dp))
}
