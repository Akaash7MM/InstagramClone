package com.example.instagramclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Post
import com.example.instagramclone.Constants.getUrl
import com.example.instagramclone.databinding.PostItemBinding

class PostsAdapterDiffUtil(
    var currentPostList: List<Post>
) : RecyclerView.Adapter<PostsAdapterDiffUtil.PostsViewHolder>() {

    inner class PostsViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context))
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = currentPostList.get(holder.bindingAdapterPosition)

        holder.binding.imgView.apply {
            Glide.with(this).load(getUrl(post.id)).into(this)
        }
    }

    override fun getItemCount(): Int {
        return currentPostList.size
    }

    class myDiffUtil(private val oldList: List<Post>, private val newList: List<Post>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.count()
        }

        override fun getNewListSize(): Int {
            return newList.count()
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList.get(oldItemPosition).contentDesc == newList.get(newItemPosition).contentDesc
        }
    }

    fun update(newList: List<Post>) {
        val callback = myDiffUtil(oldList = currentPostList, newList = newList)
        val result = DiffUtil.calculateDiff(callback)
        currentPostList = newList
        result.dispatchUpdatesTo(this)
    }
}
