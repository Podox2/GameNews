package com.example.gamenews.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass
import com.example.gamenews.BR

abstract class BaseVMFragment<VM: ViewModel, DB: ViewDataBinding>: BaseFragment<DB>() {

    protected lateinit var viewModel: VM
    abstract fun getViewModelClass(): KClass<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    protected open fun  initViewModel() {
        viewModel = getViewModel(getViewModelClass())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { this.lifecycleOwner = this@BaseVMFragment }
        binding.setVariable(BR.viewModel, viewModel)
    }
}