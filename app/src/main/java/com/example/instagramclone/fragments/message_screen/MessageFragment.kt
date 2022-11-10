package com.example.instagramclone.fragments.message_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagramclone.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private lateinit var fragmentMessageBinding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMessageBinding = FragmentMessageBinding.inflate(layoutInflater)
        return fragmentMessageBinding.root
    }
}
