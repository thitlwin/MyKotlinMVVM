package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.Result

interface UserRepositoryInterface {

    suspend fun getUser(userId: String): Result<User>

    suspend fun saveUser(user: User)

    suspend fun updateUser(userId: String, user: User)

    suspend fun deleteUser(userId: String)

    fun getUserList(): LiveData<List<User>>

    fun observeUserList(): LiveData<Result<List<User>>>
}