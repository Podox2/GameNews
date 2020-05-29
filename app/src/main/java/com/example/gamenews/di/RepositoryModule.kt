package com.example.gamenews.di

import com.example.gamenews.data.repository.newsrepository.NewsRepo
import com.example.gamenews.data.repository.newsrepository.NewsRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepo> {
        NewsRepoImpl(
            get()
        )
    }
}