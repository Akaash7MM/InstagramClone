package com.example.instagramclone.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.instagramclone.databinding.AdItemBinding
import com.example.instagramclone.databinding.PostItemBinding

sealed class PostViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class UserPost(val binding: PostItemBinding) : PostViewHolder(binding)
    class AdPost(val binding: AdItemBinding) : PostViewHolder(binding)

}
