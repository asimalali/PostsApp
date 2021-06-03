package com.mvvm.posts.data.posts.source.remote

import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.CallResult.Success
import com.mvvm.posts.data.db.AppDatabase
import com.mvvm.posts.data.db.dao.PostDao
import com.mvvm.posts.data.mappers.toPostEntity
import com.mvvm.posts.data.posts.PostsRemoteSource
import com.mvvm.posts.domain.model.Post
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRemoteSourceImp @Inject constructor(
    private val apiService : PostsApiService,
    appDatabase: AppDatabase
) : PostsRemoteSource {
    private val postDao: PostDao = appDatabase.postDao()

    override suspend fun getPosts(): CallResult<List<Post>> {
        val result = apiService.getPosts()

        if (result is Success){
//            postDao.deleteAll()
            result.data?.map { it.toPostEntity() }?.let { postDao.insertAll(it) }
        }

        Timber.d("postDao.insertAll(it): "+ postDao.getAll().value?.size)
        return apiService.getPosts()
    }


}