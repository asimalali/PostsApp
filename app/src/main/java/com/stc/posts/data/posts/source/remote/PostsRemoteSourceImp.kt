package com.stc.posts.data.posts.source.remote

import com.stc.posts.data.api.PostsApiService
import com.stc.posts.data.common.model.CallResult
import com.stc.posts.data.posts.PostsRemoteSource
import com.stc.posts.domain.model.Post
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