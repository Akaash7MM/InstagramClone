package com.example.instagramclone.fragments.profile_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramclone.adapters.PostsAdapter
import com.example.instagramclone.databinding.FragmentProfileBinding
import com.example.instagramclone.fragments.main_screen.MainScreenState
import com.example.instagramclone.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment() : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding
    private val profileScreenViewModel by viewModels<ProfileScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = FragmentProfileBinding.inflate(inflater)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val postsAdapter = PostsAdapter(onImageClick = {}, onSaveTap = {})

        collectLatestLifecycleFlow(profileScreenViewModel.uiState) { result ->
            when (result) {
                is ProfileScreenState.Success -> {
                    postsAdapter.submitList(result.savedPostList)
                }
                is ProfileScreenState.Failure -> {
                }
                is ProfileScreenState.Loading -> {
                }
                else -> Unit
            }
        }
        profileBinding.RvSavedPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }
}