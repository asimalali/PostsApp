package com.mvvm.posts.data.mappers

import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.domain.model.PostItem
import com.mvvm.posts.domain.model.PostResponse

fun PostItem.toPostEntity() = PostEntity(
    id = this.id?.toLong(),
    userId = this.userId.toString(),
    title = this.title,
    body = this.body
)
fun PostEntity.toPost() = PostItem(
        id = this.id?.toInt(),
        userId = this.userId?.toInt(),
        title = this.title,
        body = this.body
)

fun PostResponse.PostItem.toPostEntity() = PostEntity(
        id = this.id?.toLong(),
        userId = this.userId.toString(),
        title = this.title,
        body = this.body
)
