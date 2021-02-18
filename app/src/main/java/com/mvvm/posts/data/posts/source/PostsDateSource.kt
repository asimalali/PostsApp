package com.mvvm.posts.data.posts

import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.Post


interface PostsLocalSource {
  suspend fun savePosts()
}

interface PostsRemoteSource {
  suspend fun getPosts() : CallResult<List<Post>>
}