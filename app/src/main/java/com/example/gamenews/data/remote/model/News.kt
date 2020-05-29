package com.example.gamenews.data.remote.model

import com.google.gson.annotations.SerializedName

data class News (
    var title: String,
    val type: String,
    @SerializedName("img")
    val imageUrl: String,
    @SerializedName("click_url")
    val siteUrl: String,
    val time: String,
    val top: String
)