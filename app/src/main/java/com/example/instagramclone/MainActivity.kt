package com.example.instagramclone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramclone.fragments.Navigation
import com.example.instagramclone.fragments.ui.theme.InstagramCloneTheme
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = SurfaceColors.SURFACE_5.getColor(this)

        setContent {
            InstagramCloneTheme() {
                Navigation()
            }
        }
    }
}
