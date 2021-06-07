package com.mvvm.posts.domain

import androidx.lifecycle.LiveData
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.PostItem

interface PostsRepository {
    suspend fun getPosts() :CallResult<List<PostItem>>
    suspend fun getLocalPosts() : LiveData<List<PostItem>>
}