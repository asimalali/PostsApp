package com.mvvm.News.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    @SerializedName("userId")
    val userId : Int?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("id")
    val id : Int? ,

    @SerializedName("body")
    val body : String? = ""
): Parcelable


// class NewsResponse(
//        val data: List<NewsItem>
//){
//     inner class NewsItem(
//             @SerializedName("userId")
//             val userId : Int?,
//
//             @SerializedName("title")
//             val title : String?,
//
//             @SerializedName("id")
//             val id : Int? ,
//
//             @SerializedName("body")
//             val body : String? = ""
//     )
// }






class NewsResponse(
        val data: List<NewsItem>
){
    inner class NewsItem (
            @SerializedName("userId")
            val userId : Int?,

            @SerializedName("title")
            val title : String?,

            @SerializedName("id")
            val id : Int? ,

            @SerializedName("body")
            val body : String? = ""
    )
}
