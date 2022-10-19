package com.example.instagramclone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Post
import com.example.instagramclone.Constants.getUrl
import com.example.instagramclone.databinding.PostItemBinding

class PostsAdapter(
    var postList: List<Post>
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context))
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postList.get(holder.bindingAdapterPosition)
        holder.itemView.apply {
            Glide.with(this).load(getUrl(post.id)).into(holder.binding.imgView)
        }
        holder.binding.imgBtn1.setOnClickListener(
            View.OnClickListener {
                onItemOnClickListener?.let { it(post) }
            }
        )
    }

    private var onItemOnClickListener: ((Post) -> Unit)? = null

    fun setOnItemClickListener(onClick: (Post) -> Unit) {
        onItemOnClickListener = onClick
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
