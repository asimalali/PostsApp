package com.mvvm.posts.data.posts

import androidx.lifecycle.LiveData
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.PostItem


interface PostsLocalSource {
  suspend fun savePosts()
  suspend fun getLocalPosts() : LiveData<List<PostItem>>
}

interface PostsRemoteSource {
  suspend fun getPosts() : CallResult<List<PostItem>>
}