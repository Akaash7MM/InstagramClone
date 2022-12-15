package com.example.instagramclone.fragments.signup_screen.compose

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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.instagramclone.R.drawable
import com.example.instagramclone.R.string
import com.example.instagramclone.fragments.compose.components.CustomTextField
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.login_screen.compose.ClickableText
import com.example.instagramclone.fragments.ui.theme.EtGrey2
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
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                modifier = Modifier
                    .width(224.dp)
                    .height(112.dp),
                painter = painterResource(id = drawable.ic_instagram_logo),
                contentDescription = stringResource(id = string.instagram_logo)
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 54.dp)
                    .padding(bottom = 44.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.EtGrey2,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = string.sign_up_see_friends)
            )

            CustomTextField(
                text = {
                    email.value
                },
                hintText = stringResource(id = string.username_hint),
                backgroundColor = MaterialTheme.colors.secondary,
                onValueChange = {
                    email.value = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                text = {
                    fullName.value
                },
                hintText = stringResource(id = string.fullname_hint),
                backgroundColor = MaterialTheme.colors.secondary,
                onValueChange = {
                    fullName.value = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                text = {
                    username.value
                },
                hintText = stringResource(id = string.username_only_hint),
                backgroundColor = MaterialTheme.colors.secondary,
                onValueChange = {
                    username.value = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                text = {
                    password.value
                },
                hintText = stringResource(id = string.password_hint),
                backgroundColor = MaterialTheme.colors.secondary,
                onValueChange = {
                    password.value = it
                }
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 54.dp, vertical = 18.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.EtGrey2,
                textAlign = TextAlign.Center,
                text = stringResource(id = string.terms_conditions)
            )
            Button(
                modifier = Modifier.width(338.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = MaterialTheme.colors.FBlue2,
                    backgroundColor = MaterialTheme.colors.FBlue
                ),
                enabled = email.value.isNotBlank() && password.value.isNotBlank(),
                onClick = {
                    loginViewModel.createUser(email.value, password.value)
                }
            ) {
                Text(text = stringResource(id = string.sign_up_text), color = Color.White)
            }
            ClickableText(text = stringResource(id = string.have_an_account_login), color = Color.Gray, fontSize = 12.sp) {
                navController.navigate(Screen.Login.route)
            }
        }
    }
}
