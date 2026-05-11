package com.devshady.newsappkmp.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// We need a way to pass the context from the Android app
private lateinit var applicationContext: Context

fun initDatabase(context: Context) {
    applicationContext = context.applicationContext
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = applicationContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}
