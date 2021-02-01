package com.stc.posts

import android.os.Bundle
import com.stc.posts.common.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun observeUi() = Unit
    override fun setOnClickListeners() = Unit
}