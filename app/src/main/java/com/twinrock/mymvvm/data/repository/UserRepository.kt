package com.twinrock.mymvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.twinrock.mymvvm.data.local.UserDao
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.remote.AppApi
import javax.inject.Inject
import com.twinrock.mymvvm.data.MyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository @Inject constructor(
    private val appApi: AppApi,
    private val userDao: UserDao
) : UserRepositoryInterface {

    val TAG = javaClass.name

    override suspend fun getUser(userId: String): MyResult<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    override suspend fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): MyResult<List<User>> = withContext(Dispatchers.IO) {
        return@withContext try {
            MyResult.Success(userDao.getAllUsers())
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    override fun observeUserList(): LiveData<MyResult<List<User>>> {
        return userDao.observeAllUsers().map { MyResult.Success(it) }
    }
}