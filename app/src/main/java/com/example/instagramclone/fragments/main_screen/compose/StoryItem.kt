package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable

@Composable
fun StoryItem(storyItem: Post) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(78.dp),
                painter = painterResource(id = drawable.ic_story_border_rainbow),
                contentDescription = "story border"
            )
            AsyncImage(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = drawable.bg_placeholder),
                model = storyItem.imgUrlsmall,
                contentDescription = "profile image"
            )
        }
        Box(modifier = Modifier.width(78.dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = storyItem.userName,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}