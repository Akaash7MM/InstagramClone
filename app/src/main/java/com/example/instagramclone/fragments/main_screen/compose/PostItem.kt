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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.entities.Post
import com.example.instagramclone.R.drawable

@Composable
fun PostItem(postItem: Post) {
    val modifier = Modifier

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            PostTopbar(postItem = postItem, modifier = modifier)
            Box(
                modifier = modifier.fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center

            ) {
                AsyncImage(
                    modifier = modifier,
                    placeholder = painterResource(id = drawable.bg_placeholder),
                    model = postItem.imgUrlNormal,
                    contentDescription = "user post"
                )
            }
            PostBottomBar(postItem = postItem, modifier = modifier)

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
fun PostBottomBar(postItem: Post, modifier: Modifier) {
    var isPostLiked by remember {
        mutableStateOf(false)
    }
    var isPostSaved by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = modifier
                .padding(start = 15.dp)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    isPostLiked = !isPostLiked
                },
            painter = painterResource(
                id = if (isPostLiked) {
                    drawable.ic_heart_64_true
                } else {
                    drawable.ic_heart_64_false
                }
            ),
            contentDescription = "heart"

        )
        Image(
            modifier = modifier
                .padding(start = 20.dp)
                .wrapContentSize()
                .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                },
            painter = painterResource(id = drawable.ic_comment),
            contentDescription = "comment"
        )

        Image(
            modifier = modifier
                .padding(start = 20.dp)
                .wrapContentSize()
                .clickable {
                },
            painter = painterResource(id = drawable.ic_share),
            contentDescription = "share"
        )

        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = modifier
                    .padding(end = 15.dp)
                    .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                        isPostSaved = !isPostSaved
                    },
                painter = painterResource(
                    id = if (isPostSaved) {
                        drawable.ic_save_true
                    } else {
                        drawable.ic_save_false
                    }
                ),
                contentDescription = "save"

            )
        }
    }
}

@Composable
fun PostTopbar(postItem: Post, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = modifier
                .wrapContentSize()
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = modifier
                    .size(35.dp),
                painter = painterResource(id = drawable.ic_story_border_rainbow),
                contentDescription = "story border"
            )
            AsyncImage(
                modifier = modifier
                    .size(30.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = drawable.bg_placeholder),
                model = postItem.imgUrlNormal,
                contentDescription = "profile image"
            )
        }
        Text(
            modifier = modifier,
            text = postItem.userName
        )
        Box(
            modifier = modifier
                .fillMaxWidth(),
            Alignment.CenterEnd
        ) {
            Image(
                modifier = modifier.size(20.dp),
                painter = painterResource(id = drawable.ic_vertical_ellipsis),
                contentDescription = "vertical ellipsis"
            )
        }
    }
}
