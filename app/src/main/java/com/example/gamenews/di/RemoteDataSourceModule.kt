package com.example.gamenews.di

import com.example.gamenews.data.remote.service.NewsDataSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL_API = "http://188.40.167.45:3001"

val remoteDataSourceModule = module {

    fun provideCallFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    fun provideHttpClient() =
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideNewsDataSource(
        httpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): NewsDataSource =
        NewsDataSource(
            Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .client(httpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
        )


    single { provideCallFactory() }
    single { provideGson() }
    single { provideConverterFactory(get()) }
    single { provideHttpClient() }

    single { provideNewsDataSource(get(), get(), get()) }

}