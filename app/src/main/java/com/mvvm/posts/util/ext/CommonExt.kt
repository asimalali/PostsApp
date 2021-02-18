package com.mvvm.posts.util.ext

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.hide() = apply {
    visibility = View.GONE
}

fun ProgressBar.show() = apply {
    visibility = View.VISIBLE
}
