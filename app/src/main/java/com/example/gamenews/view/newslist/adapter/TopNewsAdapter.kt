package com.example.gamenews.view.newslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gamenews.data.remote.model.News
import com.example.gamenews.databinding.ItemTopNewsBinding
import com.smarteist.autoimageslider.SliderViewAdapter


class TopNewsAdapter : SliderViewAdapter<TopNewsAdapter.TopNewsViewHolder>() {

    lateinit var onUrlClick: OnUrlClick

    private val topNewsList = mutableListOf<News>()

    fun setItems(list: List<News>) {
        topNewsList.clear()
        topNewsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): TopNewsViewHolder =
        TopNewsViewHolder(
            ItemTopNewsBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        )

    override fun onBindViewHolder(viewHolder: TopNewsViewHolder?, position: Int) {
        viewHolder!!.binding.apply {
            item = topNewsList[position]
            tvUrl.setOnClickListener {
                onUrlClick.onUrlClick((item as News).siteUrl)
            }
            executePendingBindings()
        }
    }

    override fun getCount(): Int = topNewsList.size


    class TopNewsViewHolder(val binding: ItemTopNewsBinding) : ViewHolder(binding.root)
}