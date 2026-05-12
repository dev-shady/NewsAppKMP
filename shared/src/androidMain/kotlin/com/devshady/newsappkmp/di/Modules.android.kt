package com.devshady.newsappkmp.di

import com.devshady.newsappkmp.data.local.NewsDatabase
import com.devshady.newsappkmp.data.local.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { getDatabaseBuilder().build() }
    single { get<NewsDatabase>().articleDao() }
}
