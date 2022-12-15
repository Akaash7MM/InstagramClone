package com.example.data.remote.dto.video

import com.example.data.local.entities.PostEntity
import com.example.data.util.Constants.sample_video_desc
import com.example.domain.entities.Post

data class Video(
    val avg_color: Any,
    val duration: Int,
    val full_res: Any,
    val height: Int,
    val id: Int,
    val image: String,
    val tags: List<Any>,
    val url: String,
    val user: User,
    val video_files: List<VideoFile>,
    val video_pictures: List<VideoPicture>,
    val width: Int
)

fun Video.toPost(): Post {
    return Post(
        isVideo = true,
        userName = user.name,
        videoUrlHD = video_files.get(0).link,
        videoUrlSD = video_files.get(1).link,
        videoImg = image,
        videoPreview = video_pictures.first().picture,
        contentDesc = sample_video_desc
    )
}
fun Video.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        isVideo = true,
        userName = user.name,
        videoUrlHD = video_files.first().link,
        videoUrlSD = video_files.get(1).link,
        contentDesc = sample_video_desc,
        imgUrlNormal = image,
        imgUrlsmall = image,
        imgUrlOriginal = image,
        videoImg = image,
        videoPreview = video_pictures.first().picture,
        photoWidth = 0,
        photoHeight = 0

    )
}
