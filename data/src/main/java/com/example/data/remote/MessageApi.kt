package com.example.data.remote


import com.example.data.remote.dto.MessagesDTOItem
import com.example.domain.util.Resource

interface MessageApi {

    suspend fun getMessages(): Resource<List<MessagesDTOItem>>
}
