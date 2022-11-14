package com.example.data

import com.example.data.remote.dto.MessagesDTOItem
import com.example.data.remote.MessageApi
import com.example.data.util.safeResult
import com.example.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MessageApiImpl(
    private val client: HttpClient
) : MessageApi {
    override suspend fun getMessages(): Resource<List<MessagesDTOItem>> {
        return safeResult {
            client.get("https://jsonplaceholder.typicode.com/users")
                .body()
        }
    }
}
