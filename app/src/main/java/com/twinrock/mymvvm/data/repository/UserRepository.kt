package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.local.UserDao
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.remote.AppApi
import javax.inject.Inject
import com.twinrock.mymvvm.data.Result

class UserRepository @Inject constructor(
    private val appApi: AppApi,
    private val userDao: UserDao
) : UserRepositoryInterface {

    override suspend fun getUser(userId: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun updateUser(userId: String, user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }

    override fun getUserList(): LiveData<List<User>> {
        val res = userDao.getAllUsers()
        return res
    }

    override fun observeUserList(): LiveData<Result<List<User>>> {
        TODO("Not yet implemented")
    }
}