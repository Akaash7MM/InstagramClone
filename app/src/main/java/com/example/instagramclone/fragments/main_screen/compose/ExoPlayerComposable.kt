package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable

@Composable
fun ExoPlayerComposable(exoPlayer: ExoPlayer, postItem: Post, currentVisibleItem: Int, index: Int) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier,
            placeholder = painterResource(id = drawable.bg_placeholder),
            model = postItem.videoImg,
            contentScale = ContentScale.Inside,
            contentDescription = "user post"
        )
        Box(
            modifier = Modifier.matchParentSize()
        ) {
            if (index == currentVisibleItem) {
                AndroidView(modifier = Modifier.matchParentSize(), factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        this.useController = false
                    }
                })
            }
        }
    }
}