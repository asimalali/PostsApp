package com.mvvm.posts.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvm.posts.data.db.entities.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE userId LIKE :nationalId")
    fun findByUserId(nationalId: String?): LiveData<PostEntity>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(vararg users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<PostEntity>)


    @Delete
    fun deleteAll(user: PostEntity)

    @Query("DELETE FROM posts")
    fun deleteAll()
}