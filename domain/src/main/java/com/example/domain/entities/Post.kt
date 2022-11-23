package com.example.domain.entities

data class Post(
    val id: Int = 0,
    val userName: String = "natgeo",
    val imgUrlOriginal: String = " ",
    val imgUrlNormal: String = " ",
    val imgUrlsmall: String = " ",
    val contentDesc: String = "This is sample text for the description of this Instagram post, This image probably contains cool nature scenery",
    val isLiked: Boolean = false,
    val isVideo: Boolean = false,
    val videoUrlHD: String = "",
    val videoUrlSD: String = ""
)
