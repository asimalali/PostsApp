package com.mvvm.news

import android.os.Bundle
import com.mvvm.news.common.base.activity.BaseActivity
import com.mvvm.news.util.ext.withFullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        withFullScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun observeUi() = Unit
    override fun setOnClickListeners() = Unit
}