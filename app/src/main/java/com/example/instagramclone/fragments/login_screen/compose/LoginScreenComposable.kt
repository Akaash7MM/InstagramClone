package com.example.instagramclone.fragments.login_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.compose.components.GreyTextField
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.ui.theme.EtGrey
import com.example.instagramclone.fragments.ui.theme.EtGrey2
import com.example.instagramclone.fragments.ui.theme.FBlue
import com.example.instagramclone.fragments.ui.theme.FBlue2
import com.example.instagramclone.util.Screen

@Composable
fun LoginScreenComposable(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
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
                backgroundColor = MaterialTheme.colors.EtGrey,
                onValueChange = {
                    username.value = it
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
            GreyTextField(
                text = password.value,
                hintText = "Password",
                visualTransformation = PasswordVisualTransformation(),
                backgroundColor = MaterialTheme.colors.EtGrey,
                onValueChange = {
                    password.value = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(338.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.FBlue,
                    disabledBackgroundColor = MaterialTheme.colors.FBlue2
                ),
                enabled = username.value.length > 0 && password.value.length > 0,
                onClick = {
                    loginViewModel.loginUser(username.value, password.value)
                }
            ) {
                Text(text = "Log in", color = Color.White)
            }
            ClickableText(
                modifier = Modifier
                    .padding(14.dp),
                text = "Forgot your login details? Get help logging in",
                fontSize = 12.sp
            ) {
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.EtGrey2)
            )
            ClickableText(
                modifier = Modifier.padding(14.dp),
                text = "Don't have an account? Sign up",
                fontSize = 12.sp
            ) {
                navController.navigate(Screen.SignUp.route)
            }
        }
    }
}

@Composable
fun ClickableText(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified, fontSize: TextUnit = 14.sp, onClick: () -> Unit) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = modifier.clickable(interactionSource = MutableInteractionSource(), indication = null, onClick = onClick)
    )
}
