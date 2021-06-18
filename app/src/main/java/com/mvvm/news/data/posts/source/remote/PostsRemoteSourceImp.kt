package com.mvvm.News.data.News.source.remote

import com.mvvm.News.data.api.NewsApiService
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.common.model.CallResult.Success
import com.mvvm.News.data.db.AppDatabase
import com.mvvm.News.data.db.dao.NewsDao
import com.mvvm.News.data.mappers.toNewsEntity
import com.mvvm.News.data.News.NewsRemoteSource
import com.mvvm.News.domain.model.NewsItem
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteSourceImp @Inject constructor(
    private val apiService : NewsApiService,
    appDatabase: AppDatabase
) : NewsRemoteSource {
    private val NewsDao: NewsDao = appDatabase.NewsDao()

    override suspend fun getNews(): CallResult<List<NewsItem>> {
        val result = apiService.getNews()

        if (result is Success){
//            NewsDao.deleteAll()
            result.data?.map { it.toNewsEntity() }?.let { NewsDao.insertAll(it) }
        }

        Timber.d("NewsDao.insertAll(it): "+ NewsDao.getAll().value?.size)
        return apiService.getNews()
    }


}