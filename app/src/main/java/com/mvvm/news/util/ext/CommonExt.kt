package com.mvvm.news.util.ext

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso

fun ProgressBar.hide() = apply {
    visibility = View.GONE
}

fun ProgressBar.show() = apply {
    visibility = View.VISIBLE
}

fun ImageView.setImageFromUrl(url: String) {
    Picasso.with(this.context).load(url).into(this)
}
