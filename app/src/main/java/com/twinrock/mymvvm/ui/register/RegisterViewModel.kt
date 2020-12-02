package com.twinrock.mymvvm.ui.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {
    val TAG = javaClass.name

    val userList: LiveData<List<User>> = userRepository.getUserList()

    fun registerUser(user: User) {
        viewModelScope.async {
//            Log.i(TAG, "registerUser in coroutine----")
            userRepository.saveUser(user)
        }
    }
}