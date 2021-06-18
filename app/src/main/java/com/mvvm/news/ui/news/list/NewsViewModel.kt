package com.mvvm.News.ui.News.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.News.common.base.fragment.BaseViewModel
import com.mvvm.News.data.Resource
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.common.model.ErrorObject
import com.mvvm.News.data.repo.NewsRepo
import com.mvvm.News.domain.NewsRepository
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.util.NullableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel @ViewModelInject constructor(
    private val NewsRepository : NewsRepository,
    private val NewsRepo: NewsRepo
) : BaseViewModel() {

    val NewsLive = MediatorLiveData<Resource<List<NewsItem>>>()
    private var allNews: LiveData<Resource<List<NewsItem>>> = NullableLiveData.create()


    init {
        allNews = NewsRepo.getNewNews()
        NewsLive.addSource(allNews) {
            NewsLive.value = it
        }
    }

}