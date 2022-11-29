package com.example.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.instagramclone.databinding.FragmentMainScreenBinding
import com.example.instagramclone.fragments.main_screen.MainScreenState
import com.example.instagramclone.fragments.main_screen.compose.PostItem
import com.example.instagramclone.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen() : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val mainScreenViewModel by viewModels<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        collectLatestLifecycleFlow(mainScreenViewModel.uiState) { result ->
            when (result) {
                is MainScreenState.Success -> {
                    binding.rvPosts.setContent {
                        LazyColumn {
                            items(result.postList) { postItem ->
                                PostItem(postItem)
                            }
                        }
                    }
                }
                is MainScreenState.Failure -> {
                }
                is MainScreenState.Loading -> {
                }
                else -> Unit
            }
        }
    }
}
