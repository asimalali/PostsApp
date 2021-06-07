package com.mvvm.posts.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mvvm.posts.data.NetworkBoundResource
import com.mvvm.posts.data.NetworkResponse
import com.mvvm.posts.data.Resource
import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.RemoteError
import com.mvvm.posts.data.db.AppDatabase
import com.mvvm.posts.data.db.dao.PostDao
import com.mvvm.posts.data.mappers.toPost
import com.mvvm.posts.data.mappers.toPostEntity
import com.mvvm.posts.di.ApplicationScope
import com.mvvm.posts.di.IoDispatcher
import com.mvvm.posts.di.MainDispatcher
import com.mvvm.posts.domain.model.PostItem
import com.mvvm.posts.domain.model.PostResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepo @Inject constructor(
        private val apiService : PostsApiService,
        appDatabase: AppDatabase,
        @ApplicationScope
        val applicationScope: CoroutineScope,
        @MainDispatcher
        val mainDispatcher: CoroutineDispatcher,
        @IoDispatcher
        val ioDispatcher: CoroutineDispatcher
)  {
    private val postDao: PostDao = appDatabase.postDao()

     suspend fun getPosts(): CallResult<List<PostItem>> {
        val result = apiService.getPosts()

        if (result is CallResult.Success){
            postDao.deleteAll()
            result.data?.map { it.toPostEntity() }?.let { postDao.insertAll(it) }
        }
        return apiService.getPosts()
    }

    fun getNewPosts(): LiveData<Resource<List<PostItem>>> {
        return object :
                NetworkBoundResource<List<PostItem>, List<PostItem>>(
                        applicationScope,
                        mainDispatcher
                ) {
            override suspend fun saveCallResult(item: List<PostItem>) =
                    postDao.insertAll(item.map { it.toPostEntity() })

            override fun forceUpdate(data: List<PostItem>?): Boolean = false

            override suspend fun loadFromDb(): LiveData<List<PostItem>> =
                    postDao.getAll().map { items -> items.map { it.toPost() } }

            override suspend fun createCall(): NetworkResponse<List<PostItem>, RemoteError> =
                    apiService.getNewPosts()

            override fun shouldDisplayLocalVersionWhenError(): Boolean = false
        }.asLiveData()
    }
}