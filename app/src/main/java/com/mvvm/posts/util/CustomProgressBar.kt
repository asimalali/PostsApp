package com.mvvm.posts.util

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.posts.util.ext.hide
import com.mvvm.posts.util.ext.show
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CustomProgressBar @Inject constructor(@ActivityContext val context : Context) {

    private val progressBar : ProgressBar

    fun show() {
        if ((context as AppCompatActivity).isFinishing.not()) {
            progressBar.show()
        }
    }

    fun hide() {
        if ((context as AppCompatActivity).isFinishing.not()) {
            progressBar.hide()
        }
    }

    init {
        val layout = (context as Activity).findViewById<View>(R.id.content).rootView as ViewGroup
        progressBar = ProgressBar(context, null, R.attr.progressBarStyle)
        progressBar.isIndeterminate = true
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val rl = RelativeLayout(context)
        rl.gravity = Gravity.CENTER
        rl.addView(progressBar)
        layout.addView(rl, params)
        hide()
    }
}