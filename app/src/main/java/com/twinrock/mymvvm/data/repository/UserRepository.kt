package com.twinrock.mymvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
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
    override fun getUserById(userId: Int): LiveData<User> = liveData {
        try {
            emitSource(userDao.getUserById(userId))
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

//     fun getUserById(userId: Int): liveData<User>{
//
//    }

//            MyResult<User> {
//        return try {
//            MyResult.Success(userDao.getUserById(userId))
//        } catch (e: Exception) {
//            MyResult.Error(e)
//        }
//    }

    override suspend fun getUserByName(userName: String): MyResult<User> {
        return try {
            MyResult.Success(userDao.getUserByName(userName))
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    override suspend fun getUserByEmail(userEmail: String): MyResult<User> {
        return try {
            MyResult.Success(userDao.getUserByEmail(userEmail))
        } catch (e: Exception) {
            MyResult.Error(e)
        }
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