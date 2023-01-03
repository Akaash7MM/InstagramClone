package com.example.instagramclone

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.instagramclone.fragments.signup_screen.compose.TestSignUpComposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenComposableTest {
    private lateinit var navHostController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        navHostController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testIfButtonIsEnabled() {
        composeTestRule.setContent {
            TestSignUpComposable(navController = navHostController)
        }
        with(composeTestRule) {
            onNodeWithText("Phone number,username, or email").performTextInput("username")
            onNodeWithText("Password").performTextInput("password")
            onNodeWithText("Sign Up").assertIsEnabled()
        }
    }

    @Test
    fun testIfButtonIsDisabled() {
        composeTestRule.setContent {
            TestSignUpComposable(navController = navHostController)
        }
        with(composeTestRule) {
            onNodeWithText("Phone number,username, or email").performTextInput("username312")
            onNodeWithText("Sign Up").assertIsNotEnabled()
            onNodeWithText("Password").performTextInput("password")
            onNodeWithText("Sign Up").assertIsEnabled()
            onNodeWithText("password").performTextClearance()
            onNodeWithText("Sign Up").assertIsNotEnabled()
            onNodeWithText("username312").performTextClearance()
            onNodeWithText("Sign Up").assertIsNotEnabled()
        }
    }
}
