package com.devshady.newsappkmp

import android.app.Application
import com.devshady.newsappkmp.data.local.initDatabase

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDatabase(this)
    }
}
