package com.example.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.adapters.PostsAdapter
import com.example.instagramclone.adapters.StoriesAdapter
import com.example.instagramclone.databinding.FragmentMainScreenBinding
import com.example.instagramclone.fragments.main_screen.MainScreenState
import com.example.instagramclone.util.collectLatestLifecycleFlow
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        val uri = requireActivity().intent.data
        val postId = uri?.lastPathSegment
        val storiesAdapter = StoriesAdapter()
        val postsAdapter = PostsAdapter(onSaveTap = { tappedPost ->
            mainScreenViewModel.savePost(post = tappedPost)
        }, onImageClick = {
            Firebase.auth.signOut()
//            binding.rvPosts.scrollToPosition(5)
                binding.nestedScrollView2.smoothScrollTo(0, 1000, 5000)
            })
        collectLatestLifecycleFlow(mainScreenViewModel.uiState) { result ->
            when (result) {
                is MainScreenState.Success -> {
                    postsAdapter.submitList(result.postList)
                    storiesAdapter.submitList(result.postList)
                }
                is MainScreenState.Failure -> {
                }
                is MainScreenState.Loading -> {
                }
                else -> Unit
            }
        }

        binding.rvStories.apply {
            adapter = storiesAdapter
            layoutManager = LinearLayoutManager(this@MainScreen.context, RecyclerView.HORIZONTAL, false)
        }

        binding.rvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@MainScreen.context)
        }

        postId?.let {
//            val isd = it.toInt()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            binding.nestedScrollView2.smoothScrollTo(0, 1000, 5000)
        }
    }
}
