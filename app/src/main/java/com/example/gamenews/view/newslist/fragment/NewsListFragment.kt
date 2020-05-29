package com.example.gamenews.view.newslist.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.base.BaseVMFragment
import com.example.gamenews.databinding.FragmentNewsListBinding
import com.example.gamenews.view.newslist.viewmodel.NewsListViewModel
import com.example.gamenews.view.newslist.adapter.NewsListAdapter
import com.example.gamenews.view.newslist.adapter.OnUrlClick
import com.example.gamenews.view.newslist.adapter.TopNewsAdapter
import kotlin.reflect.KClass


class NewsListFragment : BaseVMFragment<NewsListViewModel, FragmentNewsListBinding>(),
    OnUrlClick {

    private val newsAdapter =
        NewsListAdapter()
    private var topNewsAdapter =
        TopNewsAdapter()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override val layoutId: Int
        get() = R.layout.fragment_news_list

    override fun getViewModelClass(): KClass<NewsListViewModel> = NewsListViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter.onUrlClick = this
        topNewsAdapter.onUrlClick = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        binding.apply {
            recView.apply {
                layoutManager = linearLayoutManager
                adapter = newsAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        val lastVisibleItemPosition =
                            linearLayoutManager.findLastVisibleItemPosition()
                        if (lastVisibleItemPosition == newsAdapter.itemCount - 1) {
                            Log.d("TAG_PAGINATION", "Last Page")
                        }
                    }
                })
            }
            imageSlider.setSliderAdapter(topNewsAdapter)
        }

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            newsAdapter.setItems(it)
        })

        viewModel.topNewsList.observe(viewLifecycleOwner, Observer {
            topNewsAdapter.setItems(it)
        })

        viewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            newsAdapter.filter(it)
        })

        viewModel.fetchNews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSearchString()
    }

    override fun onUrlClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}
