package com.stc.posts.data.common.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteError(
    val errors : ErrorObject? = null
) : Parcelable