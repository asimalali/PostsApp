package com.mvvm.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {

  override fun onCreate() {
    super.onCreate()
    instance = this
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }

  companion object {

    private lateinit var instance : MyApp

    @JvmStatic
    fun getContext() = instance.applicationContext
  }

}