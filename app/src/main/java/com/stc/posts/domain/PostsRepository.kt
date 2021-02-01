package com.stc.posts.domain

import com.stc.posts.data.common.model.CallResult
import com.stc.posts.domain.model.Post

interface PostsRepository {
    suspend fun getPosts() :CallResult<List<Post>>
}