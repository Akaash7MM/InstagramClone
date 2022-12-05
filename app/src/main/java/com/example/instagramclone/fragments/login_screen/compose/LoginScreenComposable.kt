package com.example.instagramclone.fragments.login_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.compose.components.GreyTextField
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.ui.theme.EtGrey
import com.example.instagramclone.fragments.ui.theme.FBlue

@OptIn(ExperimentalLifecycleComposeApi::class)
@Preview
@Composable
fun LoginScreenComposable() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(224.dp),
            painter = painterResource(id = drawable.ic_instagram_logo),
            contentDescription = "InstagramLogo"
        )

        GreyTextField(
            text = username.value,
            hintText = "Phone number,username, or email",
            onValueChange = {
                username.value = it
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        GreyTextField(
            text = password.value,
            hintText = "Password",
            onValueChange = {
                password.value = it
            }
        )

        ClickableText(
            text = "Forgot Password?",
            modifier = Modifier
                .padding(end = 52.dp)
                .align(Alignment.End),
            color = MaterialTheme.colors.FBlue
        ) {
        }
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.FBlue
            ),
            onClick = {
                loginViewModel.loginUser(username.value, password.value)
            }
        ) {
            Text(text = "LOG IN", color = Color.White)
        }
        ClickableText(text = "Don't have an account? Sign up") {
        }
    }

//    loginViewModel.userLoggedIn.collectAsStateWithLifecycle().value
}

@Composable
fun ClickableText(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified, onClick: () -> Unit) {
    Text(
        text,
        color = color,
        modifier = modifier.clickable(interactionSource = MutableInteractionSource(), indication = null) {
            onClick()
        }
    )
}