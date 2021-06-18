package com.mvvm.News.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mvvm.News.data.NetworkBoundResource
import com.mvvm.News.data.NetworkResponse
import com.mvvm.News.data.Resource
import com.mvvm.News.data.api.NewsApiService
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.common.model.RemoteError
import com.mvvm.News.data.db.AppDatabase
import com.mvvm.News.data.db.dao.NewsDao
import com.mvvm.News.data.mappers.toNews
import com.mvvm.News.data.mappers.toNewsEntity
import com.mvvm.News.di.ApplicationScope
import com.mvvm.News.di.IoDispatcher
import com.mvvm.News.di.MainDispatcher
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.domain.model.NewsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepo @Inject constructor(
        private val apiService : NewsApiService,
        appDatabase: AppDatabase,
        @ApplicationScope
        val applicationScope: CoroutineScope,
        @MainDispatcher
        val mainDispatcher: CoroutineDispatcher,
        @IoDispatcher
        val ioDispatcher: CoroutineDispatcher
)  {
    private val NewsDao: NewsDao = appDatabase.NewsDao()

     suspend fun getNews(): CallResult<List<NewsItem>> {
        val result = apiService.getNews()

        if (result is CallResult.Success){
            NewsDao.deleteAll()
            result.data?.map { it.toNewsEntity() }?.let { NewsDao.insertAll(it) }
        }
        return apiService.getNews()
    }

    fun getNewNews(): LiveData<Resource<List<NewsItem>>> {
        return object :
                NetworkBoundResource<List<NewsItem>, List<NewsItem>>(
                        applicationScope,
                        mainDispatcher
                ) {
            override suspend fun saveCallResult(item: List<NewsItem>) =
                    NewsDao.insertAll(item.map { it.toNewsEntity() })

            override fun forceUpdate(data: List<NewsItem>?): Boolean = false

            override suspend fun loadFromDb(): LiveData<List<NewsItem>> =
                    NewsDao.getAll().map { items -> items.map { it.toNews() } }

            override suspend fun createCall(): NetworkResponse<List<NewsItem>, RemoteError> =
                    apiService.getNewNews()

            override fun shouldDisplayLocalVersionWhenError(): Boolean = false
        }.asLiveData()
    }
}