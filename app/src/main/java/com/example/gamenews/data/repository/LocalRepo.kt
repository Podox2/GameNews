package com.example.gamenews.data.repository

import androidx.lifecycle.MutableLiveData

//search string for communication between viewpager and fragments with lists
object LocalRepo {
    private val subscribersList = mutableListOf<MutableLiveData<String>>()
    private var searchString: String = ""
    fun getSearchString() = searchString

    fun setSearchString(query: String){
        searchString = query
        notifySubs()
    }

    fun subscribe(sub: MutableLiveData<String>){
        subscribersList.add(sub)
    }

    fun unsubscribe(sub: MutableLiveData<String>){
        subscribersList.remove(sub)
    }

    private fun notifySubs(){
        subscribersList.forEach {
            it.value = searchString
        }
    }
}