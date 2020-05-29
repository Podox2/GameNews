package com.example.gamenews.data.remote.service

import com.example.gamenews.data.remote.model.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NewsService {

    @GET(".")
    fun getListNews(): Deferred<List<News>>

}