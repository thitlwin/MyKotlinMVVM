package com.twinrock.mymvvm.data.datasource

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.Result

interface UserDataSource {

    suspend fun getUser(userId: String): Result<User>

    suspend fun saveUser(user: User)

    suspend fun updateUser(userId: String, user: User)

    suspend fun deleteUser(userId: String)

    suspend fun getUserList(): LiveData<List<User>>

    fun observeUserList(): LiveData<Result<List<User>>>
}