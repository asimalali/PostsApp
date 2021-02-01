package com.stc.posts.common.base.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import javax.inject.Inject

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