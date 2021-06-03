package com.mvvm.posts.data.mappers

import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.domain.model.Post

fun Post.toPostEntity() = PostEntity(
    id = this.id?.toLong(),
    userId = this.userId.toString(),
    title = this.title,
    body = this.body
)
fun PostEntity.toPost() = Post(
        id = this.id?.toInt(),
        userId = this.userId?.toInt(),
        title = this.title,
        body = this.body
)
