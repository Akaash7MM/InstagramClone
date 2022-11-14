package com.example.domain.entities

@kotlinx.serialization.Serializable
data class Message(
    val id: Int,
    val name: String,
    val message: String
)
