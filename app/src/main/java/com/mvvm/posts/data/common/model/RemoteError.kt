package com.mvvm.posts.data.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteError(
    val errors : ErrorObject? = null
) : Parcelable