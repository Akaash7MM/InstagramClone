package com.example.data.repository

import com.example.data.PostApi
import com.example.data.local.dao.PostDao
import com.example.data.local.entities.toPost
import com.example.data.remote.dto.toPostEntity
import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.example.domain.util.safeResultFinally
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    private val api: PostApi,
    private val dao: PostDao
) : PostRepository {

    override suspend fun getPosts(): Flow<List<Post>> {
        var flow: Flow<List<Post>> = emptyFlow()

        safeResultFinally(
            block = {
                val response = api.getPosts().photos
                val postEntityList = response.map { it.toPostEntity() }
                dao.addPosts(postEntityList)
            },
            finally = {
                flow = dao.getPosts().map { postEntityList ->
                    postEntityList.map { postEntity ->
                        postEntity.toPost(postEntity)
                    }
                }
            }
        )
        return flow
    }

    override suspend fun savePost(post: Post): Boolean {
        val userId = Firebase.auth.currentUser?.uid
        val result = Firebase.firestore.collection("Users/$userId/Posts").add(post)
        return result.isSuccessful
    }

    override suspend fun getSavedPosts(): Flow<List<Post>> {
        val userId = Firebase.auth.currentUser?.uid

        return callbackFlow {
            Firebase.firestore.collection("Users/$userId/Posts")
                .addSnapshotListener { snapshot, error ->
                    snapshot?.map { documents ->
                        documents.toObject<Post>()
                    }?.let { postList ->
                        trySend(postList)
                    }
                }
            awaitClose()
        }
    }
}
