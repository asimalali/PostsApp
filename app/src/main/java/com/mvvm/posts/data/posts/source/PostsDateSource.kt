package com.mvvm.posts.data.posts

import androidx.lifecycle.LiveData
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.domain.model.Post


interface PostsLocalSource {
  suspend fun savePosts()
  suspend fun getLocalPosts() : LiveData<List<Post>>
}

interface PostsRemoteSource {
  suspend fun getPosts() : CallResult<List<Post>>
}