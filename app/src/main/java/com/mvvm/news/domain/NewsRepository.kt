package com.mvvm.news.domain

import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.model.NewsResponse

interface NewsRepository {
    suspend fun getNews() :CallResult<NewsResponse>
}