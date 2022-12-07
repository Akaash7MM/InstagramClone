package com.example.instagramclone.fragments.profile_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R.drawable

@Preview
@Composable
fun ProfileScreenTopBar() {
    TopAppBar(
        title = {
            Text(text = "Username", fontWeight = FontWeight.Bold)
        },
        actions = {
            MenuItems()
        },
        backgroundColor = Color.White,
        elevation = 1.dp
    )
}

@Composable
fun MenuItems() {
    Image(
        modifier = Modifier
            .size(22.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { },
        painter = painterResource(id = drawable.ic_add_post_false),
        contentDescription = "video call"
    )
    Spacer(modifier = Modifier.width(25.dp))
    Image(
        modifier = Modifier
            .size(22.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) { },
        painter = painterResource(id = drawable.ic_options_ham),
        contentDescription = "video call"
    )
    Spacer(modifier = Modifier.width(10.dp))
}
