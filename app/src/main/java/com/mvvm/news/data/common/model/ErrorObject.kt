package com.mvvm.News.data.common.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// here we defined expected errors from server, for example if request not success, server will always return 'message' and 'code' error
@Parcelize
data class ErrorObject(
    @SerializedName("code")
    val code : Int? = 0,

    @SerializedName("message")
    val message : String? = null

) : Parcelable