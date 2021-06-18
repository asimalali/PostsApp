package com.mvvm.News.data.mappers

import com.mvvm.News.data.db.entities.NewsEntity
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.domain.model.NewsResponse

fun NewsItem.toNewsEntity() = NewsEntity(
    id = this.id?.toLong(),
    userId = this.userId.toString(),
    title = this.title,
    body = this.body
)
fun NewsEntity.toNews() = NewsItem(
        id = this.id?.toInt(),
        userId = this.userId?.toInt(),
        title = this.title,
        body = this.body
)

fun NewsResponse.NewsItem.toNewsEntity() = NewsEntity(
        id = this.id?.toLong(),
        userId = this.userId.toString(),
        title = this.title,
        body = this.body
)
