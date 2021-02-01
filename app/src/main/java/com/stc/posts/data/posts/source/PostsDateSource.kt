package com.stc.posts.data.posts

import com.stc.posts.data.common.model.CallResult
import com.stc.posts.domain.model.Post


interface PostsLocalSource {
  suspend fun savePosts()
}

interface PostsRemoteSource {
  suspend fun getPosts() : CallResult<List<Post>>
}