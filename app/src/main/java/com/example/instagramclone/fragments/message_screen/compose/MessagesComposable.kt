package com.example.instagramclone.fragments.message_screen.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.entities.Message
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.compose.components.GreyTextField
import com.example.instagramclone.fragments.ui.theme.EtGrey2
import com.example.instagramclone.fragments.ui.theme.FBlue

@Preview
@Composable
fun MessagesComposable() {
    Scaffold(topBar = {
        MessagesTopBar()
    }) {
        Column(modifier = Modifier.padding(it)) {
            GreyTextField(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                text = "",
                hintText = "Search",
                backgroundColor = MaterialTheme.colors.EtGrey2,
                onValueChange = {
                }
            )

            AsyncImage(
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 8.dp)
                    .size(84.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = drawable.bg_placeholder),
                model = "https://images.pexels.com/photos/14072809/pexels-photo-14072809.jpeg",
                contentDescription = "profile image"
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                text = "Your Note"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(all = 12.dp),
                    text = "Messages",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(all = 12.dp),
                    text = "Requests",
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.FBlue
                )
            }

            LazyColumn() {
                items(
                    listOf(
                        Message(name = "Akashdeep", message = "This is a  sample message", id = 1),
                        Message(name = "Akashdeep", message = "This is a  sample message", id = 2),
                        Message(name = "Akashdeep", message = "This is a  sample message", id = 3),
                        Message(name = "Akashdeep", message = "This is a  sample message", id = 4)
                    )
                ) { messageItem ->
                    MessageItem()
                }
            }
        }
    }
}
