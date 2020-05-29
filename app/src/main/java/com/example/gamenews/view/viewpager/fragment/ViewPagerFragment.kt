package com.example.gamenews.view.viewpager.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.gamenews.R
import com.example.gamenews.base.BaseVMFragment
import com.example.gamenews.base.ViewPagerAdapter
import com.example.gamenews.databinding.FragmentViewPagerBinding
import com.example.gamenews.view.newslist.fragment.NewsListFragment
import com.example.gamenews.view.viewpager.viewmodel.ViewPagerViewModel
import kotlin.reflect.KClass


class ViewPagerFragment : BaseVMFragment<ViewPagerViewModel, FragmentViewPagerBinding>() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun getViewModelClass(): KClass<ViewPagerViewModel> = ViewPagerViewModel::class

    override val layoutId: Int
        get() = R.layout.fragment_view_pager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(NewsListFragment(), getString(R.string.stories))
        viewPagerAdapter.addFragment(NewsListFragment(), getString(R.string.video))
        viewPagerAdapter.addFragment(NewsListFragment(), getString(R.string.favourites))

        binding.apply {
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText ?: "")
                return true
            }
        })
    }
}
