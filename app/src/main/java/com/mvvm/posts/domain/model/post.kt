package com.mvvm.posts.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostItem(
    @SerializedName("userId")
    val userId : Int?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("id")
    val id : Int? ,

    @SerializedName("body")
    val body : String? = ""
): Parcelable


// class PostResponse(
//        val data: List<PostItem>
//){
//     inner class PostItem(
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






class PostResponse(
        val data: List<PostItem>
){
    inner class PostItem (
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
