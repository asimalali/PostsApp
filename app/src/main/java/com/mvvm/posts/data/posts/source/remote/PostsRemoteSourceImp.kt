package com.mvvm.posts.data.posts.source.remote

import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.posts.PostsRemoteSource
import com.mvvm.posts.domain.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRemoteSourceImp @Inject constructor(
    private val apiService : PostsApiService
) : PostsRemoteSource {

    override suspend fun getPosts(): CallResult<List<Post>> {
        return apiService.getPosts()
    }


}