package com.example.instagramclone.fragments.signup_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.compose.components.GreyTextField
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.login_screen.compose.ClickableText
import com.example.instagramclone.fragments.ui.theme.EtGrey
import com.example.instagramclone.fragments.ui.theme.FBlue
import com.example.instagramclone.fragments.ui.theme.FBlue2
import com.example.instagramclone.util.Screen

@Composable
fun SignUpComposable(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val email = remember {
        mutableStateOf("")
    }
    val fullName = remember {
        mutableStateOf("")
    }
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .width(224.dp)
                .height(112.dp),
            painter = painterResource(id = drawable.ic_instagram_logo),
            contentDescription = "InstagramLogo"
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 54.dp)
                .padding(bottom = 45.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Sign up to see photos and videos from your friends"
        )

        GreyTextField(
            text = email.value,
            hintText = "Phone number,username, or email",
            backgroundColor = MaterialTheme.colors.EtGrey,
            onValueChange = {
                email.value = it
            }
        )
        Spacer(modifier = Modifier.height(14.dp))
        GreyTextField(
            text = fullName.value,
            hintText = "Full Name",
            backgroundColor = MaterialTheme.colors.EtGrey,
            onValueChange = {
                fullName.value = it
            }
        )
        Spacer(modifier = Modifier.height(14.dp))
        GreyTextField(
            text = username.value,
            hintText = "Username",
            backgroundColor = MaterialTheme.colors.EtGrey,
            onValueChange = {
                username.value = it
            }
        )
        Spacer(modifier = Modifier.height(14.dp))
        GreyTextField(
            text = password.value,
            hintText = "Password",
            backgroundColor = MaterialTheme.colors.EtGrey,
            onValueChange = {
                password.value = it
            }
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 54.dp, vertical = 20.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            textAlign = TextAlign.Center,
            text = "By signing up, you agree to our Terms, Privacy Policy and Cookies Policy"
        )
        Button(
            modifier = Modifier.width(338.dp),
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.FBlue2,
                backgroundColor = MaterialTheme.colors.FBlue
            ),
            enabled = email.value.length > 0 && password.value.length > 0,
            onClick = {
                loginViewModel.createUser(email.value, password.value)
            }
        ) {
            Text(text = "SIGN UP", color = Color.White)
        }
        ClickableText(text = "Have an account? Log in", color = Color.Gray, fontSize = 12.sp) {
            navController.navigate(Screen.Login.route)
        }
    }
}
