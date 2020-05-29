package com.example.gamenews.view.newslist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.gamenews.base.BaseViewHolder
import com.example.gamenews.data.remote.model.News
import com.example.gamenews.databinding.ItemNewsBinding
import java.util.*

class NewsListAdapter : ListAdapter<News, BaseViewHolder<ItemNewsBinding>>(NewsDiffCallBack) {

    companion object NewsDiffCallBack : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onUrlClick: OnUrlClick

    private val newsListCopy = mutableListOf<News>()

    fun setItems(news: List<News>) {
        submitList(news)
        newsListCopy.clear()
        newsListCopy.addAll(news)
    }

    fun filter(query: String) {
        var text = query
        submitList(listOf())
        if (text.isEmpty()) {
            submitList(newsListCopy)
        } else {
            text = text.toLowerCase(Locale.getDefault())
            val tempList = mutableListOf<News>()
            for (news in newsListCopy) {
                if (news.title.toLowerCase(Locale.getDefault()).contains(text)) {
                    tempList.add(news)
                }
            }
            submitList(tempList)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemNewsBinding> =
        BaseViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: BaseViewHolder<ItemNewsBinding>, position: Int) {
        holder.binding.apply {
            item = getItem(position)
            tvUrl.setOnClickListener {
                onUrlClick.onUrlClick((item as News).siteUrl)
            }
            executePendingBindings()
        }
    }
}