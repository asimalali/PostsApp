package com.mvvm.posts.data.posts.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.db.AppDatabase
import com.mvvm.posts.data.db.dao.PostDao
import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.data.mappers.toPost
import com.mvvm.posts.data.mappers.toPostEntity
import com.mvvm.posts.data.posts.PostsLocalSource
import com.mvvm.posts.domain.model.Post
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsLocalSourceImp @Inject constructor(
        appDatabase: AppDatabase,
        private val apiService : PostsApiService
) : PostsLocalSource {

    private val postDao: PostDao = appDatabase.postDao()

    override suspend fun savePosts() {
    }

    override suspend fun getLocalPosts(): LiveData<List<Post>> {
        Timber.d("postDao.getAll(): "+ postDao.getAll())
        val result = apiService.getPosts()

        if (result is CallResult.Success){
            postDao.deleteAll()
            result.data?.map { it.toPostEntity() }?.let { postDao.insertAll(it) }
        }
        Timber.d("postDao.getAll(): "+ postDao.getAll().value?.size)
        return postDao.getAll().map { it.map { it.toPost() } }
    }
}