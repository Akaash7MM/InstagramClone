package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.PostDao
import com.example.data.local.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = true)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}
