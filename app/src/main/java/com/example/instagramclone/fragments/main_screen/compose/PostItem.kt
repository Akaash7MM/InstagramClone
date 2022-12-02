package com.example.instagramclone.fragments.main_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    postItem: Post,
    savePost: () -> Unit,
    exoPlayer: ExoPlayer,
    currentVisibleItem: Int,
    index: Int
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PostTopbar(postItem = postItem, modifier = Modifier)
            PostItemContent(exoPlayer = exoPlayer, postItem = postItem, currentVisibleItem = currentVisibleItem, index = index)
            PostBottomBar(savePost = { savePost() })
            Text(
                modifier = modifier
                    .padding(horizontal = 15.dp),
                text = "2,426" + " likes",
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = modifier
                    .padding(horizontal = 15.dp),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(postItem.userName)
                        append(" ")
                    }
                    append(postItem.contentDesc)
                }
            )
            Text(
                modifier = modifier
                    .padding(horizontal = 15.dp),
                text = "View all 83 comments",
                style = TextStyle(color = Color.Gray)
            )
            Text(
                modifier = modifier
                    .padding(horizontal = 15.dp),
                text = "4 Hours ago",
                style = TextStyle(color = Color.Gray)
            )
        }
    }
}

@Composable
fun PostBottomBar(savePost: () -> Unit) {
    var isPostLiked by remember {
        mutableStateOf(false)
    }
    var isPostSaved by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            BottomBarIcon(
                onClick = {
                    isPostLiked = !isPostLiked
                },
                painter = painterResource(
                    id = if (isPostLiked) {
                        drawable.ic_heart_64_true
                    } else {
                        drawable.ic_heart_64_false
                    }
                )
            )
            BottomBarIcon(
                onClick = { },
                painter = painterResource(id = drawable.ic_comment)
            )

            BottomBarIcon(
                onClick = { },
                painter = painterResource(id = drawable.ic_share)
            )
        }
        BottomBarIcon(
            onClick = {
                isPostSaved = !isPostSaved
                savePost()
            },
            painter = painterResource(
                id = if (isPostSaved) {
                    drawable.ic_save_true
                } else {
                    drawable.ic_save_false
                }
            )
        )
    }
}

@Composable
fun BottomBarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter
) {
    Icon(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                onClick()
            },
        painter = painter,
        contentDescription = "BottomBarIcon"

    )
}

@Composable
fun PostTopbar(postItem: Post, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(35.dp),
                    painter = painterResource(id = drawable.ic_story_border_rainbow),
                    contentDescription = "story border"
                )
                AsyncImage(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = drawable.bg_placeholder),
                    model = if (postItem.isVideo) {
                        postItem.videoImg
                    } else {
                        postItem.imgUrlNormal
                    },
                    contentDescription = "profile image"
                )
            }
            Text(
                modifier = Modifier,
                text = postItem.userName
            )
        }
        Image(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(id = drawable.ic_vertical_ellipsis),
            contentDescription = "vertical ellipsis"
        )
    }
}
