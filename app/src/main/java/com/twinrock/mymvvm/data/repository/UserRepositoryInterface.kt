package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.MyResult

interface UserRepositoryInterface {

    suspend fun getUser(userId: String): MyResult<User>

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(userId: String)

    fun observeUserList(): LiveData<MyResult<List<User>>>

    suspend fun getUsers(): MyResult<List<User>>
}