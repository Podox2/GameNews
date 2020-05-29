package com.example.gamenews.base

import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.gamenews.R

object BindingAdapter {

    @BindingAdapter(value = ["drawImage"], requireAll = false)
    @JvmStatic
    fun drawImage(view: AppCompatImageView, url: String?) {
        var stringUrl = url
        if (TextUtils.isEmpty(stringUrl)) stringUrl = null
        Glide.with(view.context)
            .load(stringUrl)
            .fitCenter()
            .into(view)
    }


    @BindingAdapter(value = ["drawImageOrDefault"], requireAll = false)
    @JvmStatic
    fun drawImageOrDefault(view: AppCompatImageView, url: String?) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(view.context)
                .load(R.drawable.image)
                .fitCenter()
                .into(view)
        } else {
            Glide.with(view.context)
                .load(url)
                .fitCenter()
                .into(view)
        }
    }

}