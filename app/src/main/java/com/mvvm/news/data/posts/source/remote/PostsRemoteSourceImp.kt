package com.mvvm.news.data.News.source.remote

import com.mvvm.news.data.api.NewsApiService
import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.data.News.NewsRemoteSource
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.model.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteSourceImp @Inject constructor(
    private val apiService : NewsApiService
) : NewsRemoteSource {

    override suspend fun getNews(): CallResult<NewsResponse> {
        return apiService.getNews()
    }


}