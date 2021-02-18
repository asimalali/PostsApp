package com.mvvm.posts.domain

import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.Post

interface PostsRepository {
    suspend fun getPosts() :CallResult<List<Post>>
}