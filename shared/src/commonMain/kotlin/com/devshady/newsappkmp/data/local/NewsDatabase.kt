package com.devshady.newsappkmp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.devshady.newsappkmp.data.local.dao.ArticleDao
import com.devshady.newsappkmp.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}

// Room KMP requires an actual/expect constructor for the database
@Suppress("KotlinNoActualForExpect")
expect object NewsDatabaseConstructor : RoomDatabaseConstructor<NewsDatabase> {
    override fun initialize(): NewsDatabase
}

internal const val DATABASE_NAME = "news.db"
