package com.stc.posts.util.ext

import android.R
import android.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showErrorDialog(message: String?) {
    val builder = AlertDialog.Builder(context)
        .setTitle("Sorry ..")
        .setMessage(message)
        .setCancelable(true)

    val alert = builder.create()
    alert.show()
}