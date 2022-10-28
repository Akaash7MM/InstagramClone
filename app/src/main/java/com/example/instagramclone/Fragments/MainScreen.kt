package com.example.instagramclone.Fragments

import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Post
import com.example.instagramclone.Adapters.PostsAdapter
import com.example.instagramclone.Adapters.StoriesAdapter
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentMainScreenBinding

class MainScreen : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val postsList = mutableListOf(
            Post(1, isAd = true),
            Post(2),
            Post(3, isAd = true),
            Post(4, isAd = true),
            Post(5),
            Post(6,isAd = true),
            Post(7),
            Post(8,isAd = true),
            Post(9)
        )

        val postsAdapter = PostsAdapter(onImageClick = {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainScreen_to_searchScreen)
        })

        val storiesAdapter = StoriesAdapter()

        postsAdapter.submitList(postsList)
        storiesAdapter.submitList(postsList)

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
