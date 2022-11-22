package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.Post

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val imgUrlOriginal: String,
    val imgUrlsmall: String,
    val contentDesc: String,
    val imgUrlNormal: String
)

fun PostEntity.toPost(postEntity: PostEntity): Post {
    return Post(
        id = postEntity.id,
        userName = postEntity.userName,
        imgUrlOriginal = postEntity.imgUrlOriginal,
        imgUrlsmall = postEntity.imgUrlsmall,
        contentDesc = postEntity.contentDesc,
        imgUrlNormal = postEntity.imgUrlNormal
    )
}
