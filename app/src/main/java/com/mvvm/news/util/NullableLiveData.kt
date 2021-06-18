package com.mvvm.News.util

import androidx.lifecycle.LiveData

class NullableLiveData<T : Any?> private constructor() : LiveData<T>() {
    /**
     * A LiveData class that has `null` value.
     */
    init {
        // use News instead of set since this can be created on any thread
//        NewsValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return NullableLiveData()
        }
    }
}