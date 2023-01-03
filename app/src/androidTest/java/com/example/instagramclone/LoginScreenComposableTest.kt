package com.example.instagramclone

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.usecases.GetCreateUserUseCase
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetLoginUserUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import com.example.instagramclone.fragments.login_screen.LoginViewModel
import com.example.instagramclone.fragments.login_screen.compose.LoginScreenComposable
import com.example.instagramclone.fragments.login_screen.compose.TestLoginScreenComposable
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenComposableTest {
    private lateinit var navHostController: TestNavHostController

    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK lateinit var testAuthRepository: AuthRepositoryImpl
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        hiltTestRule.inject()
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(
            getLoginUserUseCase = GetLoginUserUseCase(testAuthRepository),
            getFetchDetailsUseCase = GetFetchDetailsUseCase(testAuthRepository),
            getSaveDetailsUseCase = GetSaveDetailsUseCase(testAuthRepository),
            getCreateUserUseCase = GetCreateUserUseCase(testAuthRepository)
        )
        navHostController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testIfButtonIsEnabled() {
        composeTestRule.setContent {
            LoginScreenComposable(navController = navHostController, loginViewModel)
        }
        with(composeTestRule) {
            onNodeWithText("Phone number,username, or email").performTextInput("username")
            onNodeWithText("Password").performTextInput("password")
            onNodeWithText("Log In").assertIsEnabled()
        }
    }

    @Test
    fun testIfButtonIsDisabled() {
        composeTestRule.setContent {
            TestLoginScreenComposable(navController = navHostController)
        }
        with(composeTestRule) {
            onNodeWithText("Log in").assertIsNotEnabled()
            onNodeWithText("Phone number,username, or email").performTextInput("username312")
            onNodeWithText("Log in").assertIsNotEnabled()
            onNodeWithText("Password").performTextInput("password312")
            onNodeWithText("Log in").assertIsEnabled()
        }
    }
}
