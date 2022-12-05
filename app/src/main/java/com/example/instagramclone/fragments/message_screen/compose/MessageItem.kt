package com.example.instagramclone.fragments.message_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.instagramclone.R.drawable

@Preview
@Composable
fun MessageItem() {
    Row(
        modifier = Modifier
            .height(74.dp)
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp),
                    painter = painterResource(id = drawable.ic_story_border_rainbow),
                    contentDescription = "story border"
                )
                AsyncImage(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = drawable.bg_placeholder),
                    model = "https://images.pexels.com/photos/14072809/pexels-photo-14072809.jpeg",
                    contentDescription = "profile image"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Username")
                Text("Sent 10m ago", color = Color.Gray)
            }
        }

        Image(
            modifier = Modifier
                .size(22.dp),
            painter = painterResource(id = drawable.ic_camera_outline),
            contentDescription = "camera outline"
        )
    }
}
