package com.example.gamenews.view.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamenews.data.remote.model.News
import com.example.gamenews.data.repository.LocalRepo
import com.example.gamenews.data.repository.newsrepository.NewsRepo
import com.example.gamenews.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsRepo: NewsRepo) : BaseViewModel() {

    private val _topNewsList = MutableLiveData<List<News>>()
    val topNewsList: LiveData<List<News>>
        get() = _topNewsList

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    init {
        LocalRepo.subscribe(_searchQuery)
    }

    fun fetchNews() {
        viewModelScope.launch {
            showProgress()
            if (_newsList.value == null) {
                _newsList.value = newsRepo.getNews().await()
                //get only top news
                _topNewsList.value = _newsList.value?.filter {
                    it.top == "1"
                }
            }
            hideProgress()
        }
    }

    fun getSearchString(){
        _searchQuery.value = LocalRepo.getSearchString()
    }

    override fun onCleared() {
        super.onCleared()
        LocalRepo.unsubscribe(_searchQuery)
    }
}