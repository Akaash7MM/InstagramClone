package com.example.instagramclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
            Post("Dummy", R.drawable.sampleimg),
            Post("Dummy", R.drawable.sampleimg),
            Post("Dummy", R.drawable.sampleimg),
            Post("Dummy", R.drawable.sampleimg),
            Post("Dummy", R.drawable.sampleimg)
        )

        val postsAdapter = PostsAdapter(postsList)

        binding.rvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@MainScreen.context)
        }
    }
}
