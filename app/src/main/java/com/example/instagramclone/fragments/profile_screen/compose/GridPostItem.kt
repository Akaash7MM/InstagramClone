package com.example.instagramclone.fragments.profile_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable

@Composable
fun GridPostItem(savedPostItem: Post) {
    AsyncImage(
        modifier = Modifier
            .padding(1.dp)
            .size(144.dp)
            .clickable { },
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = drawable.bg_placeholder),
        model = savedPostItem.imgUrlOriginal,
        contentDescription = "profile image"
    )
}
