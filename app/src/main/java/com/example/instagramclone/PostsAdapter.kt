package com.example.instagramclone

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Post
import com.example.instagramclone.Constants.getUrl
import com.example.instagramclone.PostsAdapter.PostsViewHolder
import com.example.instagramclone.databinding.PostItemBinding

class PostsAdapter : ListAdapter<Post, PostsViewHolder>(myDiffUtil()) {

    inner class PostsViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context))
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = currentList.get(holder.bindingAdapterPosition)
        holder.binding.imgView.apply {
            Glide.with(this).load(getUrl(post.id)).into(this)
        }
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }

    class myDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}
