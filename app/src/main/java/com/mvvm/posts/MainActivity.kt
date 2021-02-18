package com.mvvm.posts

import android.os.Bundle
import com.mvvm.posts.common.base.activity.BaseActivity
import com.mvvm.posts.util.ext.withFullScreen
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