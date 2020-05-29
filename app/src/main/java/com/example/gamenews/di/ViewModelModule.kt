package com.example.gamenews.di

import com.example.gamenews.view.newslist.viewmodel.NewsListViewModel
import com.example.gamenews.view.viewpager.viewmodel.ViewPagerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsListViewModel(
            get()
        )
    }
    viewModel { ViewPagerViewModel() }
}