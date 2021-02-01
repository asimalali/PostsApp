package com.stc.posts.data.posts.source.local

import com.stc.posts.data.posts.PostsLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsLocalSourceImp @Inject constructor(
) : PostsLocalSource {

    override suspend fun savePosts() {
    }
}