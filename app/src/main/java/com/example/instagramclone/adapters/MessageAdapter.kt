package com.example.instagramclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.domain.entities.Message
import com.example.instagramclone.adapters.MessageAdapter.MessageViewHolder
import com.example.instagramclone.adapters.MessageAdapter.urlUtil.getUrl
import com.example.instagramclone.databinding.MessageItemBinding

class MessageAdapter : ListAdapter<Message, MessageViewHolder>(MessageDiffUtil()) {

    inner class MessageViewHolder(val binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = currentList.get(holder.bindingAdapterPosition)
        holder.binding.userName.text = message.name
        holder.binding.msgPreview.text = message.message
        holder.binding.userImage.apply {
            load(getUrl(message.id)) {
                transformations(CircleCropTransformation())
            }
        }
    }

    class MessageDiffUtil : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }
    object urlUtil {
        fun getUrl(id: Int): String {
            return "https://picsum.photos/id/${id + 10}/500/500"
        }
    }
}
