package com.example.instagramclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.entities.Post
import com.example.instagramclone.adapters.VideoAdapter.VideoViewHolder
import com.example.instagramclone.databinding.LayoutVideoListItemBinding
import com.example.instagramclone.databinding.VideoItemBinding

class VideoAdapter(var currentList: List<Post>) : RecyclerView.Adapter<VideoViewHolder>() {

    inner class VideoViewHolder(val binding: LayoutVideoListItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(LayoutVideoListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.itemView.apply {
            setTag(this)
        }
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }
}
