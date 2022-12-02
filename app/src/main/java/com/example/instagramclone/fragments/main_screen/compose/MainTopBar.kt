package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramclone.R.drawable

@Preview
@Composable
fun MainTopBar() {
    TopAppBar(
        title = {
            Image(
                modifier = Modifier.size(108.dp),
                painter = painterResource(id = drawable.ic_instagram_logo),
                contentDescription = "InstagramLogo"
            )
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
        modifier = Modifier.size(22.dp),
        painter = painterResource(id = drawable.ic_add_post_false),
        contentDescription = "addpost"
    )
    Spacer(modifier = Modifier.width(25.dp))
    Image(
        modifier = Modifier
            .size(22.dp)
            .clickable {
            },
        painter = painterResource(id = drawable.ic_messenger),
        contentDescription = "messenger"
    )
    Spacer(modifier = Modifier.width(10.dp))
}
