package com.mvvm.News.common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setOnClickListeners()
        observeUi()
    }

    abstract fun observeUi()

    abstract fun setOnClickListeners()




}