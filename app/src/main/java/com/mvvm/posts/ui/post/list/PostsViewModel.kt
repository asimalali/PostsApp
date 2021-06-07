package com.mvvm.posts.ui.post.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.posts.common.base.fragment.BaseViewModel
import com.mvvm.posts.data.Resource
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.data.repo.PostRepo
import com.mvvm.posts.domain.PostsRepository
import com.mvvm.posts.domain.model.PostItem
import com.mvvm.posts.util.NullableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PostsViewModel @ViewModelInject constructor(
    private val postsRepository : PostsRepository,
    private val postRepo: PostRepo
) : BaseViewModel() {

    val postsLive = MediatorLiveData<Resource<List<PostItem>>>()
    private var allPosts: LiveData<Resource<List<PostItem>>> = NullableLiveData.create()


    init {
        allPosts = postRepo.getNewPosts()
        postsLive.addSource(allPosts) {
            postsLive.value = it
        }
    }

}