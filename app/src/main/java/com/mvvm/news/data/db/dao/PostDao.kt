package com.mvvm.News.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvm.News.data.db.entities.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    fun getAll(): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM News WHERE userId LIKE :nationalId")
    fun findByUserId(nationalId: String?): LiveData<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(News: List<NewsEntity>)

    @Delete
    fun deleteAll(user: NewsEntity)

    @Query("DELETE FROM News")
    fun deleteAll()
}