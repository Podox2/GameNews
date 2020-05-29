package com.example.gamenews.data.repository.newsrepository

import com.example.gamenews.data.remote.model.News
import com.example.gamenews.data.remote.service.NewsDataSource
import com.example.gamenews.data.repository.newsrepository.NewsRepo
import kotlinx.coroutines.Deferred

class NewsRepoImpl(private val newsDataSource: NewsDataSource) : NewsRepo {
    override suspend fun getNews(): Deferred<List<News>> = newsDataSource.getListNews()
}