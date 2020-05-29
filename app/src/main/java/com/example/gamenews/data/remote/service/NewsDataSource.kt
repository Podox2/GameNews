package com.example.gamenews.data.remote.service

import com.example.gamenews.data.remote.model.News
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit

class NewsDataSource(retrofit: Retrofit) : NewsService {

    private val api = retrofit.create(NewsService::class.java)

    override fun getListNews(): Deferred<List<News>> = api.getListNews()
}