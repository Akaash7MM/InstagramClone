package com.example.instagramclone.fragments.profile_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R
import com.example.instagramclone.R.drawable
import com.example.instagramclone.R.string

@Preview
@Composable
fun ProfileScreenTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = string.username_text), fontWeight = FontWeight.Bold)
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
        painter = painterResource(id = drawable.ic_add_post_false),
        contentDescription = stringResource(id = R.string.addpost_logo)
    )
    Spacer(modifier = Modifier.width(24.dp))
    Icon(
        modifier = Modifier
            .size(22.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { },
        painter = painterResource(id = drawable.ic_options_ham),
        contentDescription = stringResource(id = R.string.hambar_icon)
    )
    Spacer(modifier = Modifier.width(8.dp))
}
