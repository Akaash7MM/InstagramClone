package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.domain.entities.Post

@Composable
fun StoriesSection(postList: List<Post>) {
    LazyRow() {
        items(postList) { postItem ->
            StoryItem(
                storyItem = postItem
            )
        }
    }
}