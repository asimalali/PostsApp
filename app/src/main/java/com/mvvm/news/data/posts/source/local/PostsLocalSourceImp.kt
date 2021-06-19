package com.mvvm.news.data.News.source.local

import com.mvvm.news.data.News.NewsLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalSourceImp @Inject constructor(
) : NewsLocalSource {

    override suspend fun saveNews() {
    }
}