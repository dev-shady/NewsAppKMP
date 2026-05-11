package com.devshady.newsappkmp.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/" + DATABASE_NAME
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory = { NewsDatabaseConstructor.initialize() }
    )
}
