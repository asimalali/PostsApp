package com.stc.posts.common.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.stc.posts.util.CustomProgressBar
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