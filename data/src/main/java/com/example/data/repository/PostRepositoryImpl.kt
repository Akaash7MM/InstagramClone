package com.example.data.repository

import com.example.data.PostApi
import com.example.data.remote.dto.toPost
import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class PostRepositoryImpl(
    val api: PostApi
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        val postList = api.getPosts().photos.map { it.toPost() }
        return postList
    }

    override suspend fun savePost(post: Post) : Boolean {
        val result = Firebase.firestore.collection("Posts").add(post)
        return result.isSuccessful
    }
}
