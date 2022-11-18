package com.example.data.remote.dto

import android.os.Parcelable


data class PostDTO(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)



