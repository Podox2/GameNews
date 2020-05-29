package com.example.gamenews

import android.app.Application
import com.example.gamenews.di.remoteDataSourceModule
import com.example.gamenews.di.repositoryModule
import com.example.gamenews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(viewModelModule, remoteDataSourceModule, repositoryModule))
        }
    }
}