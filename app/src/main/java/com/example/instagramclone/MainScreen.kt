package com.example.instagramclone

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Post
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val postsList = mutableListOf(
            Post(1),
            Post(2),
            Post(3),
            Post(4),
            Post(5),
            Post(6),
            Post(7),
            Post(8),
            Post(9)
        )

        val postsAdapter = PostsAdapter()
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
