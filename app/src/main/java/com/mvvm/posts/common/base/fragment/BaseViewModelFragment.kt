package com.mvvm.posts.common.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel

abstract class BaseViewModelFragment : BaseFragment() {

    abstract val viewModel : ViewModel

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUi()
    }

    abstract fun observeUi()
}