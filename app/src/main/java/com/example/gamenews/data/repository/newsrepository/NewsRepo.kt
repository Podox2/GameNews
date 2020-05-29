package com.example.gamenews.data.repository.newsrepository

import com.example.gamenews.data.remote.model.News
import kotlinx.coroutines.Deferred

interface NewsRepo {
    suspend fun getNews() : Deferred<List<News>>
}