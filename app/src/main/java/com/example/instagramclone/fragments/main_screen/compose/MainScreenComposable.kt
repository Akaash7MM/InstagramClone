package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer.Builder
import androidx.navigation.NavHostController
import com.example.domain.entities.Post
import com.example.instagramclone.fragments.MainScreenViewModel
import com.example.instagramclone.fragments.compose.components.BottomBar
import com.example.instagramclone.fragments.main_screen.MainScreenState
import com.example.instagramclone.util.Screen

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreenComposable(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by mainScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MainTopBar(onMessagesClick = {
                navController.navigate(Screen.Message.route)
            })
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        }
    ) { paddingValues ->
        AnimatedContent(targetState = uiState) { dataState ->
            when (dataState) {
                is MainScreenState.Success -> {
                    MainScreen(
                        modifier = Modifier
                            .padding(paddingValues),
                        postList = dataState.postList,
                        savePost = { mainScreenViewModel.savePost(it) }
                    )
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, savePost: (Post) -> Unit, postList: List<Post>) {
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
    LazyColumn(
        modifier = modifier,
        state = lazyColumnState
    ) {
        item {
            StoriesSection(postList)
        }
        itemsIndexed(postList) { index, postItem ->
            PostItem(
                postItem = postItem,
                savePost = { savePost(postItem) },
                exoPlayer = exoPlayer,
                isVisible = index == visibleItem.value
            )
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
}