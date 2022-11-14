package com.example.data.remote.dto

@kotlinx.serialization.Serializable
data class Geo(
    val lat: String,
    val lng: String
)