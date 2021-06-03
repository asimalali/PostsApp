package com.mvvm.posts.data.repo

import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.db.AppDatabase
import com.mvvm.posts.data.db.dao.PostDao
import com.mvvm.posts.data.mappers.toPostEntity
import com.mvvm.posts.domain.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepo @Inject constructor(
        private val apiService : PostsApiService,
        appDatabase: AppDatabase
)  {
    private val postDao: PostDao = appDatabase.postDao()

     suspend fun getPosts(): CallResult<List<Post>> {
        val result = apiService.getPosts()

        if (result is CallResult.Success){
            postDao.deleteAll()
            result.data?.map { it.toPostEntity() }?.let { postDao.insertAll(it) }
        }
        return apiService.getPosts()
    }

//    fun getNewPosts(): LiveData<Resource<List<PostEntity>>> {
//        return object :
//                NetworkBoundResource<List<PostEntity>, PostEntity>(
//                        applicationScope,
//                        mainDispatcher
//                ) {
//            override suspend fun saveCallResult(item: PostEntity) {
//                item.let {
//                    postDao.insertAll(it)
//                 }
//            }
//
//            override fun forceUpdate(data: List<PostEntity>?): Boolean =
//                    resultsCacheMeter.forceUpdate(KEY_DAILY_SURVEY) || data.isNullOrEmpty()
//
//            override fun shouldDisplayLocalVersionWhenError(): Boolean = false
//
//            override suspend fun loadFromDb(): LiveData<List<PostEntity>> =
//                    postDao.getAll()
//
//            override suspend fun createCall(): NetworkResponse<TetammanSurveyQuestionsDTO, RemoteError> =
//                    serviceApi.getSurveyQuestions(appPrefs.locale)
//
//        }.asLiveData()
//    }
}