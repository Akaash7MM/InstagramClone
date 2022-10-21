package com.example.instagramclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Post
import com.example.instagramclone.StoriesAdapter.StoriesViewHolder
import com.example.instagramclone.databinding.StoryItemBinding

class StoriesAdapter : ListAdapter<Post, StoriesViewHolder>(StoryDiffUtil()) {

    inner class StoriesViewHolder(val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val post = currentList.get(holder.bindingAdapterPosition)
        holder.binding.storyImage.apply {
            Glide.with(this).load(Constants.getUrl(post.id)).override(125,125).into(this)
        }
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
