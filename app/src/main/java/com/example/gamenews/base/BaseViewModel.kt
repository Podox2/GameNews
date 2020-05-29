package com.example.gamenews.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val isShowProgress = ObservableBoolean()

    fun hideProgress() {
        isShowProgress.set(false)
    }

    fun showProgress() {
        isShowProgress.set(true)
    }
}