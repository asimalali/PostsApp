package com.mvvm.posts.data.api

import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.Post
import retrofit2.http.*


interface PostsApiService {

  @GET("posts")
  suspend fun getPosts() : CallResult<List<Post>>
}
