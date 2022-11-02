package com.example.instagramclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.Post
import com.example.instagramclone.adapters.StoriesAdapter.StoriesViewHolder
import com.example.instagramclone.databinding.StoryItemBinding

class StoriesAdapter : ListAdapter<Post, StoriesViewHolder>(StoryDiffUtil()) {

    inner class StoriesViewHolder(val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val post = currentList.get(holder.bindingAdapterPosition)
//        holder.binding.storyImage.apply {
//            Glide.with(this).load(post.imgUrlNormal).centerCrop().into(this)
//        }
        holder.binding.storyUsername.text = post.userName
    }

    class StoryDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}
