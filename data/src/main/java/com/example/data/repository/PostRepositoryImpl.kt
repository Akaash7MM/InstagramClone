package com.example.data.repository

import com.example.data.PostApi
import com.example.data.local.dao.PostDao
import com.example.data.local.entities.toPost
import com.example.data.remote.dto.photo.toPostEntity
import com.example.data.remote.dto.video.toPostEntity
import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.example.domain.util.CoroutineDispatcherProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val api: PostApi,
    private val dao: PostDao,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : PostRepository {

    override fun getPosts(): Flow<List<Post>> {
        return dao.getPosts().map { postEntityList ->
            postEntityList.map { postEntity ->
                postEntity.toPost(postEntity)
            }
        }.flowOn(coroutineDispatcherProvider.io)
    }

    override suspend fun fetchPosts() {
        withContext(coroutineDispatcherProvider.io) {
            val photoResponse = api.getPhotoPost().photos
            val videoResponse = api.getVideoPosts().videos

            val photoEntityList = photoResponse.map { it.toPostEntity() }
            val videoEntityList = videoResponse.map { it.toPostEntity() }

            val finalList = photoEntityList.plus(videoEntityList)

            dao.addPosts(finalList)
        }
    }

    override suspend fun savePost(post: Post): Boolean {
        val result = withContext(coroutineDispatcherProvider.io) {
            val userId = Firebase.auth.currentUser?.uid
            val result = Firebase.firestore.collection("Users/$userId/Posts").add(post)
            result.isSuccessful
        }
        return result
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
        }.flowOn(coroutineDispatcherProvider.io)
    }
}
