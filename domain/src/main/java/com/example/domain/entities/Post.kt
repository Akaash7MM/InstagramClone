package com.example.domain.entities

data class Post(
    val id: Int = 0,
    val userName: String = "natgeo",
    val isAd: Boolean = false,
    val imgUrlOriginal: String,
    val imgUrlsmall: String,
    val contentDesc: String = "This is sample text for the description of this Instagram post, This image probably contains cool nature scenery",
    var isLiked: Boolean = false,
    val imgUrlNormal: String
)
