package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun PostItemContent(exoPlayer: ExoPlayer, postItem: Post, currentVisibleItem: Int, index: Int) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(targetState = (index == currentVisibleItem) && postItem.isVideo) { showVideo ->
            if (showVideo) {
                AndroidView(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .wrapContentHeight(),
                    factory = { context ->
                        PlayerView(context).apply {
                            player = exoPlayer
                            this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                            this.useController = false
                        }
                    }
                )
            } else {
                AsyncImage(
                    modifier = Modifier,
                    placeholder = painterResource(id = drawable.bg_placeholder),
                    model = postItem.imgUrlOriginal,
                    contentScale = ContentScale.Inside,
                    contentDescription = "user post"
                )
            }
        }
    }
}