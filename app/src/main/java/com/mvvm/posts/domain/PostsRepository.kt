package com.mvvm.posts.domain

import androidx.lifecycle.LiveData
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.domain.model.Post

interface PostsRepository {
    suspend fun getPosts() :CallResult<List<Post>>
    suspend fun getLocalPosts() : LiveData<List<Post>>
}