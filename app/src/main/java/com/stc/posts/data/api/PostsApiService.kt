package com.stc.posts.data.api

import com.stc.posts.data.common.model.CallResult
import com.stc.posts.domain.model.Post
import retrofit2.http.*


interface PostsApiService {

  @GET("posts")
  suspend fun getPosts() : CallResult<List<Post>>
}
