package com.stc.posts.ui.post.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.stc.posts.common.base.fragment.BaseViewModel
import com.stc.posts.common.state.SingleLiveEvent
import com.stc.posts.data.common.model.CallResult
import com.stc.posts.data.common.model.ErrorObject
import com.stc.posts.domain.PostsRepository
import com.stc.posts.domain.model.Post
import kotlinx.coroutines.launch
import timber.log.Timber


class PostsViewModel @ViewModelInject constructor(
    private val postsRepository : PostsRepository
) : BaseViewModel() {

    private val _uiState = MutableLiveData<PostsState>()
    val uiState : LiveData<PostsState> = _uiState

    init {
        getPosts()
    }
    private fun getPosts() {
            viewModelScope.launch {
                _uiState.postValue(PostsState.Loading)
                val result = postsRepository.getPosts()
                when (result) {
                    is CallResult.Success -> {
                        _uiState.postValue(PostsState.Success(result.data!!))
                        Timber.d("SIZE: ${result.data!!.size}")
                    }
                    is CallResult.Fail -> {
                        _uiState.postValue(PostsState.Error(result.error))
                    }
                }
            }
    }

    sealed class PostsState {
        object Loading : PostsState()
        data class Error(val error : ErrorObject) : PostsState()
        data class Success(val posts : List<Post>) : PostsState()

    }

}