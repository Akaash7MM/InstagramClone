package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(postList: List<PostEntity>)

    @Query("SELECT * FROM post_table ORDER BY id asc")
    fun getPosts(): Flow<List<PostEntity>>
}
