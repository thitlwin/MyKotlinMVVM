package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import com.twinrock.mymvvm.data.datasource.UserDataSource
import com.twinrock.mymvvm.data.local.UserDao
import com.twinrock.mymvvm.data.model.LoggedInUser
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.remote.ApiService
import javax.inject.Inject
import com.twinrock.mymvvm.data.Result

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserDataSource {

    override suspend fun getUser(userId: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userId: String, user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserList(): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override fun observeUserList(): LiveData<Result<List<User>>> {
        TODO("Not yet implemented")
    }

    fun login(email: String, password: String): LoggedInUser {
        // handle login
        val result = userDao.getUserByEmail(email)
        val loggedUser = LoggedInUser("1", email)

        return loggedUser
    }

    fun logout() {

    }
}