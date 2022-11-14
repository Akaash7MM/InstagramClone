package com.example.data.remote.dto

import com.example.domain.entities.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessagesDTOItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun MessagesDTOItem.toMessage(): Message {
    return Message(
        id = id,
        name = name,
        message = company.bs
    )
}
