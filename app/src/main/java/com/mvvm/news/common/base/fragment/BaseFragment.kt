package com.mvvm.news.common.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.mvvm.news.util.CustomProgressBar
import javax.inject.Inject


// if we have fragment with no viewModel
abstract class BaseFragment : Fragment() {

    abstract fun setOnClickListeners()

    @Inject
    lateinit var progressBar : CustomProgressBar

    @CallSuper
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    open fun showLoading() = progressBar.show()

    open fun hideLoading() = progressBar.hide()
}