package com.mvvm.news.util.ext

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun ProgressBar.hide() = apply {
    visibility = View.GONE
}

fun ProgressBar.show() = apply {
    visibility = View.VISIBLE
}

fun ImageView.setImageFromUrl(url: String) {
    Picasso.with(this.context).load(url).into(this)
}

fun getDateWithoutTime(
    pattern : String = "yyyy-MM-dd'T'HH:mm:ss",
    dateString : String?,
    locale : Locale = Locale.ENGLISH
) : String {
    return if (dateString.isNullOrEmpty()) ""
    else {
        val format : DateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        val date = format.parse(dateString)
        return SimpleDateFormat("yyyy/MM/dd", locale).format(date)
    }
}

