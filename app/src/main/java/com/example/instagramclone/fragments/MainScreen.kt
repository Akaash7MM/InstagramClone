package com.example.instagramclone.fragments

import MainScreenState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.adapters.PostsAdapter
import com.example.instagramclone.adapters.StoriesAdapter
import com.example.instagramclone.databinding.FragmentMainScreenBinding
import com.example.instagramclone.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen() : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel by viewModels<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storiesAdapter = StoriesAdapter()
        val postsAdapter = PostsAdapter(onImageClick = {
            val action = MainScreenDirections.actionMainScreenToSearchScreen(2345)
            Navigation.findNavController(binding.root).navigate(action)
        })
        collectLatestLifecycleFlow(viewModel.uiState) { result ->
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
    }
}
