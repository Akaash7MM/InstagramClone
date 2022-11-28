package com.example.data.remote.dto.video

data class VideoDTO(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<Video>
)
