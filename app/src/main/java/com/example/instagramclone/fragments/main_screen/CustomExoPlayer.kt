package com.example.instagramclone.fragments.main_screen

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.ExoPlayer.Builder
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.domain.entities.Post
import com.example.instagramclone.adapters.PostViewHolder
import com.example.instagramclone.adapters.PostViewHolder.VideoPost

class CustomExoPlayer(context: Context, val postList: List<Post>) {
    var playPosition = -1
    var progressBar: ProgressBar? = null
    var playerView: PlayerView
    var exoPlayer: ExoPlayer
    var frameLayout: FrameLayout? = null
    var videoViewHolder: VideoPost? = null
    var thumbnail: ImageView? = null
    var isVideoViewAdded: Boolean = false
    val TAG = "CustomExoPlayer"

    init {
        val ctx = context.applicationContext
        exoPlayer = Builder(ctx).build()
        playerView = PlayerView(ctx)
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        playerView.useController = false
        playerView.player = exoPlayer
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    ExoPlayer.STATE_READY -> {
                        if (!isVideoViewAdded) {
                            addVideoView()
                        }
                    }
                    else -> Unit
                }
            }
        })
    }

    fun playVideo(layoutManager: LinearLayoutManager) {
        val targetPosition: Int
        val startPosition = (layoutManager).findFirstVisibleItemPosition()

        var endPosition = (layoutManager).findLastVisibleItemPosition()

        // if there is more than 2 list-items on the screen, set the difference to be 1
        if (endPosition - startPosition > 1) {
            endPosition = startPosition + 1
        }

        // something is wrong. return.
        if (startPosition < 0 || endPosition < 0) {
            return
        }
        // if there is more than 1 list-item on the screen
        targetPosition = if (startPosition != endPosition) {
            val startPositionVideoHeight: Int = getVisibleVideoSurfaceHeight(layoutManager, startPosition)
            val endPositionVideoHeight: Int = getVisibleVideoSurfaceHeight(layoutManager, endPosition)
            Log.d(
                TAG,
                "Height for view at $startPosition is  $startPositionVideoHeight : Height for view at $endPosition is $endPositionVideoHeight"
            )
            if (startPositionVideoHeight < endPositionVideoHeight) startPosition else endPosition
        } else {
            startPosition
        }

        if (playPosition == targetPosition) {
            Log.d(TAG, "Returned")
            return
        }
        playPosition = targetPosition

        val child = layoutManager.findViewByPosition(targetPosition)
        if (child == null) {
            Log.d(
                TAG,
                "Child Null at $targetPosition"
            )
            return
        }
        // If this tag cannot be casted to VideoPost i.e it is UserPost it returns
        val holder = child.tag as? VideoPost ?: return
        resetVideoView()
        videoViewHolder = holder
        progressBar = videoViewHolder?.binding?.progressBar
        frameLayout = videoViewHolder?.binding?.mediaContainer
        thumbnail = videoViewHolder?.binding?.thumbnail
        playerView.player = exoPlayer

        exoPlayer.setMediaItem(MediaItem.fromUri(postList[targetPosition].videoUrlHD))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    private fun getVisibleVideoSurfaceHeight(layoutManager: LayoutManager, position: Int): Int {
        val videoView = (layoutManager as LinearLayoutManager).findViewByPosition(position)
        val rect = Rect(0, 0, 0, 0)
        val holder = videoView?.tag as? PostViewHolder
        when (holder) {
            is VideoPost -> {
                holder.binding.mediaContainer.getLocalVisibleRect(rect)
            }
            else -> Unit
        }
        return rect.top
    }

    private fun addVideoView() {
        frameLayout!!.addView(playerView)
        isVideoViewAdded = true
        playerView.requestFocus()
        playerView.setVisibility(RecyclerView.VISIBLE)
        thumbnail!!.visibility = RecyclerView.INVISIBLE
    }

    // Remove the old player
    private fun removeVideoView(videoView: PlayerView) {
        val parent = videoView.parent as? ViewGroup ?: return
        val index = parent.indexOfChild(videoView)
        if (index >= 0) {
            isVideoViewAdded = false
            parent.removeViewAt(index)
        }
    }
    fun resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(playerView)
            playPosition = -1
            playerView.setVisibility(RecyclerView.INVISIBLE)
            thumbnail!!.visibility = RecyclerView.VISIBLE
        }
    }
}
