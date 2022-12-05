package com.example.instagramclone.fragments.signup_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.R.drawable
import com.example.instagramclone.fragments.compose.components.GreyTextField
import com.example.instagramclone.fragments.login_screen.compose.ClickableText
import com.example.instagramclone.fragments.ui.theme.FBlue

@Preview
@Composable
fun SignUpComposable() {
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
            text = "",
            hintText = "Phone number,username, or email",
            onValueChange = {
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        GreyTextField(
            text = "",
            hintText = "Full Name",
            onValueChange = {
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        GreyTextField(
            text = "",
            hintText = "Username",
            onValueChange = {
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        GreyTextField(
            text = "",
            hintText = "Password",
            onValueChange = {
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
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.FBlue
            ),
            onClick = {
            }
        ) {
            Text(text = "SIGN UP", color = Color.White)
        }
        ClickableText(text = "Have an account? Log in", color = Color.Gray) {
        }
    }
}