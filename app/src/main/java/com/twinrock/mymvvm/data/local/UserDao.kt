package com.twinrock.mymvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.twinrock.mymvvm.data.MyResult
import com.twinrock.mymvvm.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): LiveData<User>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): User

    @Query("SELECT * FROM users WHERE name = :name")
    fun getUserByName(name: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers() : List<User>

    @Query("SELECT * FROM users")
    fun observeAllUsers(): LiveData<List<User>>


}