package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.MyResult

interface UserRepositoryInterface {

    fun getUserById(userId: Int): LiveData<User>
    suspend fun getUserByName(userName: String): MyResult<User>
    suspend fun getUserByEmail(userEmail: String): MyResult<User>

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(userId: String)

    fun observeUserList(): LiveData<MyResult<List<User>>>

    suspend fun getUsers(): MyResult<List<User>>
}