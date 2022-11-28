package com.example.data.remote.dto.photo

import com.example.data.local.entities.PostEntity
import com.example.domain.entities.Post

data class Photo(
    val alt: String,
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int
)
fun Photo.toPost(): Post {
    return Post(
        imgUrlOriginal = src.original,
        imgUrlNormal = src.large2x,
        imgUrlsmall = src.tiny,
        contentDesc = alt,
        userName = photographer
    )
}
fun Photo.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        isVideo = false,
        imgUrlOriginal = src.original,
        imgUrlNormal = src.large2x,
        imgUrlsmall = src.tiny,
        contentDesc = alt,
        userName = photographer,
        videoUrlSD = "",
        videoUrlHD = "",
        videoImg = "",
        videoPreview = ""
    )
}
