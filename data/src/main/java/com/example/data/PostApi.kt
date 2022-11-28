package com.example.data

import com.example.data.remote.dto.photo.PostDTO
import com.example.data.remote.dto.video.VideoDTO
import retrofit2.http.GET

interface PostApi {

    @GET("/v1/curated")
    suspend fun getPhotoPost(): PostDTO

    @GET("videos/popular")
    suspend fun getVideoPosts(): VideoDTO
}
