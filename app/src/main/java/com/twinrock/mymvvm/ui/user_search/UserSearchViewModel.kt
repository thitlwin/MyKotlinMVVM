package com.twinrock.mymvvm.ui.user_search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinrock.mymvvm.data.MyResult
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserSearchViewModel @ViewModelInject constructor(val userRepository: UserRepository) : ViewModel() {

    val TAG = javaClass.name

    private var _displayLoading = MutableLiveData<Boolean>(false)
    val displayLoading: LiveData<Boolean> = _displayLoading

    private var _isUserExist = MutableLiveData<Boolean>(true)
    val isUserExist: LiveData<Boolean> = _isUserExist

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun searchUserById(id: Int): LiveData<User> {
        _displayLoading.value = true
        var res = userRepository.getUserById(id)

        viewModelScope.launch {
            delay(1000)// to see progress dialog - not required when data is large amount to search
        }
        _displayLoading.value = false
        return res
    }

    suspend fun searchUserByName(name: String): User? {
        //use 'async' coroutine builder
        _displayLoading.value = true
        val result = viewModelScope.async(Dispatchers.IO){
            userRepository.getUserByName(name)
        }.await()

        delay(1000)// to see progress dialog - not required when data is large amount to search

        _displayLoading.value = false
        if (result is MyResult.Success) {
            return result.data
        }
        return null
    }

    fun searchUserByEmail(email: String) {
        _displayLoading.value = true
        //use 'launch' coroutine builder
        viewModelScope.launch(Dispatchers.IO){

            delay(1000)// to see progress dialog - not required when data is large amount to search

            userRepository.getUserByEmail(email).let { myResult ->
                if (myResult is MyResult.Success)
                    userFound(myResult.data)
                else
                    userNotFound()

                _displayLoading.postValue(false)
            }
        }
    }

    fun userFound(data: User) {
        _user.postValue(data)
        _isUserExist.postValue(true)
    }

    fun userNotFound() {
        _isUserExist.postValue(false)
    }
}