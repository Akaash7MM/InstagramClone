package com.example.instagramclone.fragments.profile_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.ui.theme.EtGrey2

@Preview
@Composable
fun ProfileScreenComposable() {
    Scaffold(topBar = {
        ProfileScreenTopBar()
    }) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            columns = GridCells.Fixed(3)
        ) {
            item(span = {
                GridItemSpan(3)
            }) {
                ProfileDetailSection()
            }
            item(span = {
                GridItemSpan(3)
            }) {
                EditProfileButtons()
            }

            items(12) {
                GridPostItem()
            }
        }
    }
}

@Composable
fun EditProfileButtons() {
    Column() {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .height(36.dp)
                    .width(334.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.EtGrey2
                ),
                onClick = {
                }
            ) {
                Text(text = "Edit profile")
            }
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .width(36.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colors.EtGrey2)
                    .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(modifier = Modifier.size(18.dp), painter = painterResource(id = drawable.ic_person_plus), contentDescription = "")
            }
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = drawable.ic_post_grid_staggered),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun ProfileDetailSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(84.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = drawable.bg_placeholder),
                model = "https://images.pexels.com/photos/14072809/pexels-photo-14072809.jpeg",
                contentDescription = "profile image"
            )
            ProfileDetailItem(
                modifier = Modifier.weight(1f),
                count = "12",
                itemType = "Posts"
            )
            ProfileDetailItem(
                modifier = Modifier.weight(1f),
                count = "273",
                itemType = "Followers"
            )
            ProfileDetailItem(
                modifier = Modifier.weight(1f),
                count = "543",
                itemType = "Following"
            )
        }
        Text("Full Name text here", fontWeight = FontWeight.Bold)
        Text("Sample Bio text")
    }
}

@Composable
fun ProfileDetailItem(
    modifier: Modifier = Modifier,
    count: String,
    itemType: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = itemType)
    }
}
