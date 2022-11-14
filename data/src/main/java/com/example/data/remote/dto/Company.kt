package com.example.data.remote.dto

@kotlinx.serialization.Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)