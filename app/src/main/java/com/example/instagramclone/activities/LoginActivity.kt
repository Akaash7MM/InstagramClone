package com.example.instagramclone.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramclone.databinding.LoginActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var loginActivityBinding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        loginActivityBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(loginActivityBinding.root)
    }
}
