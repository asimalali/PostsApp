package com.mvvm.News.domain

import androidx.lifecycle.LiveData
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.domain.model.NewsItem

interface NewsRepository {
    suspend fun getNews() :CallResult<List<NewsItem>>
    suspend fun getLocalNews() : LiveData<List<NewsItem>>
}