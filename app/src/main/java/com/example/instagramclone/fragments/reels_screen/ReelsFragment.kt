package com.example.instagramclone.fragments.reels_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.instagramclone.databinding.FragmentReelsBinding
import javax.inject.Inject

class ReelsFragment @Inject constructor(
    private val exoPlayer: ExoPlayer
) : Fragment() {
    private lateinit var reelsBinding: FragmentReelsBinding
    private var player: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reelsBinding = FragmentReelsBinding.inflate(inflater)
        return reelsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        initializePlayer()

        val mediaItem = MediaItem.fromUri("https://player.vimeo.com/external/294394804.sd.mp4?s=0ae63fad00aa2702fd154632d9ed93fb7d7ee543&profile_id=165&oauth2_token_id=57447761")
        reelsBinding.videoView.player = exoPlayer
        reelsBinding.videoView.player?.apply {
            setMediaItem(mediaItem)
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                reelsBinding.videoView.player = exoPlayer
            }
    }
}
