package com.mvvm.News.data.News.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mvvm.News.data.api.NewsApiService
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.db.AppDatabase
import com.mvvm.News.data.db.dao.NewsDao
import com.mvvm.News.data.mappers.toNews
import com.mvvm.News.data.mappers.toNewsEntity
import com.mvvm.News.data.News.NewsLocalSource
import com.mvvm.News.domain.model.NewsItem
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalSourceImp @Inject constructor(
        appDatabase: AppDatabase,
        private val apiService : NewsApiService
) : NewsLocalSource {

    private val NewsDao: NewsDao = appDatabase.NewsDao()

    override suspend fun saveNews() {
    }

    override suspend fun getLocalNews(): LiveData<List<NewsItem>> {
        return NewsDao.getAll().map { it.map { it.toNews() } }
    }
}