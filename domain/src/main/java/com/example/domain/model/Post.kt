package com.example.domain.model

data class Post(
    val id: Int,
    val userName: String = "natgeo",
    val isAd: Boolean = false,
    val contentDesc: String = "This is sample text for the description of this Instagram post, This image probably contains cool nature scenery",
    var isLiked: Boolean = false
)
