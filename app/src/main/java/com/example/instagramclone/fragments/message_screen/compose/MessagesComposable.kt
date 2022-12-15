package com.example.instagramclone.fragments.message_screen.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.domain.entities.Message
import com.example.instagramclone.R
import com.example.instagramclone.R.drawable
import com.example.instagramclone.R.string
import com.example.instagramclone.fragments.compose.components.CustomTextField
import com.example.instagramclone.fragments.message_screen.MessageViewModel
import com.example.instagramclone.fragments.ui.theme.FBlue
import com.example.instagramclone.util.ScreenState.Success

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalAnimationApi::class)
@Composable
fun MessagesComposable(
    messageViewModel: MessageViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(topBar = {
        MessagesTopBar(navController = navController)
    }) {
        val uiState by messageViewModel.uiState.collectAsStateWithLifecycle()

        AnimatedContent(targetState = uiState) { dataState ->
            when (dataState) {
                is Success -> {
                    MessageScreen(
                        modifier = Modifier.padding(it),
                        messageList = dataState.data

                    )
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun MessageScreen(modifier: Modifier = Modifier, messageList: List<Message>) {
    Column(modifier = modifier) {
        LazyColumn() {
            item {
                MesssageUtilSection()
            }
            items(
                messageList
            ) { messageItem ->
                MessageItem(messageItem)
            }
        }
    }
}

@Composable
fun MesssageUtilSection() {
    CustomTextField(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        text = { "" },
        hintText = "Search",
        backgroundColor = MaterialTheme.colors.secondary,
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
        model = "https://picsum.photos/id/${19}/500/500",
        contentDescription = stringResource(id = string.profile_image)
    )
    Text(
        modifier = Modifier
            .padding(horizontal = 32.dp),
        text = stringResource(id = R.string.your_note_text)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(all = 12.dp),
            text = stringResource(id = R.string.messages_text),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            modifier = Modifier.padding(all = 12.dp),
            text = stringResource(id = R.string.requests_text),
            fontSize = 16.sp,
            color = MaterialTheme.colors.FBlue
        )
    }
}
