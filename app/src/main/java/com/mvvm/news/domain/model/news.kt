package com.mvvm.news.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    @SerializedName("userId")
    val userId : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("id")
    val id : Int ,

    @SerializedName("body")
    val body : String = ""
): Parcelable

@Parcelize
data class NewsResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<NewsArticle> = emptyList()
): Parcelable

@Parcelize
data class NewsArticle(

//    @SerializedName("source")
//    val source: Source,

    /**
     * Name of the author for the article
     */
    @SerializedName("author")
    val author: String? = null,

    /**
     * Title of the article
     */
    @SerializedName("title")
    val title: String? = null,

    /**
     * Complete description of the article
     */
    @SerializedName("description")
    val description: String? = null,

    /**
     * URL to the article
     */
    @SerializedName("url")
    val url: String? = null,

    /**
     * URL of the artwork shown with article
     */
    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    /**
     * Date-time when the article was published
     */
    @SerializedName("publishedAt")
    val publishedAt: String? = null,

    @SerializedName("content")
    val content: String? = null
): Parcelable
//    data class Source(
//
//        @SerializedName("id")
//        val id: String? = null,
//
//        @SerializedName("name")
//        val name: String? = null
//    )

