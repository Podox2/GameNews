package com.example.gamenews.view.viewpager.viewmodel

import com.example.gamenews.data.repository.LocalRepo
import com.example.gamenews.base.BaseViewModel

class ViewPagerViewModel : BaseViewModel() {
    fun setSearchQuery(query: String){
        LocalRepo.setSearchString(query)
    }
}