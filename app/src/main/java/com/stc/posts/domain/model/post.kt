package com.stc.posts.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    @SerializedName("userId")
    val userId : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("id")
    val id : Int ,

    @SerializedName("body")
    val body : String = ""
): Parcelable