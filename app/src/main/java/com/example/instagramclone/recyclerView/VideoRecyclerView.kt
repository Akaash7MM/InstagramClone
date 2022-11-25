
import android.content.Context
import android.util.AttributeSet
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Post
import com.example.instagramclone.adapters.VideoAdapter.VideoViewHolder

class VideoRecyclerView(
    context: Context,
    attributeSet: AttributeSet
) : RecyclerView(context, attributeSet) {

    var playerView: PlayerView
    var exoPlayer: ExoPlayer
    private var playPosition = -1
    lateinit var postList: List<Post>

    init {
        val appContext = context.applicationContext
        exoPlayer = ExoPlayer.Builder(context).build()
        playerView = PlayerView(appContext)
        playerView.player = exoPlayer

        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    playVideo()
                }
            }
        })
    }

    fun playVideo() {
        val targetPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//        playPosition = targetPosition

        val child = getChildAt(targetPosition)
        val holder: VideoViewHolder = child.tag as VideoViewHolder
//        playerView = holder.binding.
//        playerView.player = exoPlayer
        exoPlayer.setMediaItem(MediaItem.fromUri("https://player.vimeo.com/external/289258217.hd.mp4?s=5cf87d7670d96bbd2c110f4dc97fd5116f4468ad&profile_id=174&oauth2_token_id=57447761"))
        exoPlayer.play()
    }

}
