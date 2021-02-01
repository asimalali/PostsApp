package com.stc.posts.util.ext

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.withFullScreen(isNavBarHidden : Boolean = false) {
    // Action bar view..
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window?.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    // Nav bar view..
    if (isNavBarHidden) window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}
