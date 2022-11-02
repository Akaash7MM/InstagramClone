package com.example.instagramclone.adapters

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.text.bold
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.domain.entities.Post
import com.example.instagramclone.R
import com.example.instagramclone.databinding.AdItemBinding
import com.example.instagramclone.databinding.PostItemBinding

class PostsAdapter(
    private val onImageClick: () -> Unit
) : ListAdapter<Post, PostViewHolder>(myDiffUtil()), OnClickListener {

    private lateinit var valueAnimator: ValueAnimator
    private lateinit var objectAnimator: ObjectAnimator

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return when (viewType) {
            R.layout.ad_item -> {
                PostViewHolder.AdPost(AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            R.layout.post_item -> {
                PostViewHolder.UserPost(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> throw IllegalArgumentException("BadView")
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = currentList.get(holder.bindingAdapterPosition)

        when (holder) {
            is PostViewHolder.AdPost -> {
                holder.binding.postImage.apply {
                    Glide.with(this).load(post.imgUrlNormal).into(this)
                }

                holder.binding.likeheartFilled.setOnClickListener {
                    setAnimationToObject(it)
                }
                holder.binding.postImage.setOnClickListener() {
                    onImageClick()
                }

                val desc = SpannableStringBuilder()
                    .bold { append(post.userName) }
                    .append(" ")
                    .append(post.contentDesc)

                holder.binding.contentDesc.text = desc
            }
            is PostViewHolder.UserPost -> {
                holder.binding.postImage.apply {
                    Glide.with(this).load(post.imgUrlOriginal).placeholder(R.drawable.testplaceholder2).into(this)

                    setOnClickListener {
                        onImageClick()
                    }
                }
                holder.binding.profileImage.apply {
                    Glide.with(this).load(post.imgUrlNormal).centerCrop().into(this)
                }

                holder.binding.likeheartFilled.setOnClickListener {
                    setAnimationToObject(it)
                }
                holder.binding.topUsername.text = post.userName

                val desc = SpannableStringBuilder()
                    .bold { append(post.userName) }
                    .append(" ")
                    .append(post.contentDesc)

                holder.binding.contentDesc.text = desc
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList.get(position).isAd) {
            false -> R.layout.post_item
            true -> R.layout.ad_item
        }
    }

    fun setAnimationToValue(view: View) {
        valueAnimator = ValueAnimator.ofFloat(0F, 0.1F, 0.2F, 0.3F, 0.4F, 0.5F, 0.6F, 0.7F, 0.8F, 0.9F, 1F)
        valueAnimator.setDuration(2000)
        valueAnimator.interpolator = (OvershootInterpolator())
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                val alpha = animation.getAnimatedValue() as Float
                view.alpha = alpha
            }
        })

        when (view.alpha) {
            0f -> valueAnimator.start()
            1f -> valueAnimator.reverse()
        }
    }
    fun setAnimationToObject(view: View) {
        objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
        objectAnimator.duration = 2000

        when (view.alpha) {
            0f -> objectAnimator.start()
            1f -> objectAnimator.reverse()
        }
    }

    class myDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onClick(v: View?) {
    }
}
