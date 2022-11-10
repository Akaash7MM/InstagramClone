package com.example.data

import com.example.data.remote.dto.PostDTO
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("/v1/curated")
    suspend fun getPosts(): Response<PostDTO>
}
