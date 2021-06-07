package com.mvvm.posts.data.api

import com.mvvm.posts.data.NetworkResponse
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.RemoteError
import com.mvvm.posts.domain.model.PostItem
import com.mvvm.posts.domain.model.PostResponse
import retrofit2.http.*


interface PostsApiService {

  @GET("posts")
  suspend fun getPosts() : CallResult<List<PostItem>>


  @GET("posts")
  suspend fun getNewPosts() : NetworkResponse<List<PostItem>, RemoteError>

}
