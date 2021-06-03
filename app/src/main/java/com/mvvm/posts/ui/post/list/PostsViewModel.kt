package com.mvvm.posts.ui.post.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.posts.common.base.fragment.BaseViewModel
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.data.db.entities.PostEntity
import com.mvvm.posts.data.repo.PostRepo
import com.mvvm.posts.domain.PostsRepository
import com.mvvm.posts.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class PostsViewModel @ViewModelInject constructor(
    private val postsRepository : PostsRepository,
    private val postRepo: PostRepo
) : BaseViewModel() {

    private val _uiState = MutableLiveData<PostsState>()
    val uiState : LiveData<PostsState> = _uiState

    init {
        getPosts()
//        getLocalPosts()
    }
    private fun getPosts() {
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.postValue(PostsState.Loading)
                val result = postsRepository.getPosts()
//                val result = postRepo.getPosts()
                when (result) {
                    is CallResult.Success -> {
                        _uiState.postValue(PostsState.Success(result.data!!))
                    }
                    is CallResult.Fail -> {
                        _uiState.postValue(PostsState.Error(result.error))
                    }
                }
            }
    }

    private fun getLocalPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(PostsState.Loading)
            val result = postsRepository.getLocalPosts()
            _uiState.postValue(PostsState.Success(result.value))

        }
    }

    sealed class PostsState {
        object Loading : PostsState()
        data class Error(val error : ErrorObject) : PostsState()
        data class Success(val posts : List<Post>?) : PostsState()
    }

}