package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.entities.Post

@Composable
fun StoriesSection(postList: List<Post>) {
    LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
        items(postList) { postItem ->
            StoryItem(
                storyItem = postItem
            )
        }
    }
}