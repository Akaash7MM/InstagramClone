package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer.Builder
import com.example.domain.entities.Post

@Composable
fun MainScreenComposable(
    postList: List<Post>,
    savePost: (Post) -> Unit
) {
    val TAG = "MainScreenComposable"
    val context = LocalContext.current
    val lazyColumnState = rememberLazyListState()
    val visibleItem = remember { derivedStateOf { lazyColumnState.firstVisibleItemIndex } }
    val exoPlayer = remember {
        Builder(context).build()
    }

    LaunchedEffect(key1 = visibleItem.value) {
        val post = postList[visibleItem.value]
        if (post.isVideo) {
            exoPlayer.setMediaItem(MediaItem.fromUri(post.videoUrlHD))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }
    Scaffold(topBar = {
        MainTopBar()
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            state = lazyColumnState
        ) {
            item {
                StoriesSection(postList)
            }
            itemsIndexed(postList) { index, postItem ->
                PostItem(
                    postItem = postItem,
                    savePost = {
                        savePost(postItem)
                    },
                    exoPlayer = exoPlayer,
                    isVisible = index == visibleItem.value
                )
            }
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
}