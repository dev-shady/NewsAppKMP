package com.devshady.newsappkmp.data.local

import androidx.room.RoomDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>
