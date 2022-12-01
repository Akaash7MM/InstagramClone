package com.example.instagramclone.fragments.main_screen.compose

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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
        exoPlayer.removeMediaItems(0, 0)
        exoPlayer.setMediaItem(MediaItem.fromUri(postList.get((visibleItem.value)).videoUrlHD))
        Log.d(TAG, visibleItem.value.toString())
        exoPlayer.prepare()

        exoPlayer.playWhenReady = true
    }
    LazyColumn(state = lazyColumnState) {
        item {
            StoriesSection(postList)
        }
        itemsIndexed(postList) { index, postItem ->
            PostItem(
                index = index,
                postItem = postItem,
                savePost = {
                    savePost(postItem)
                },
                exoPlayer = exoPlayer,
                currentVisibleItem = visibleItem.value
            )
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
}