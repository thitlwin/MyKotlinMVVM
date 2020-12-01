package com.twinrock.mymvvm.ui.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {
    val TAG = javaClass.name

    fun registerUser(user: User) {
        viewModelScope.launch {
            Log.i(TAG, "registerUser in coroutine----")
            val res = userRepository.getUserList()
            Log.i(TAG, "before save : user list at DB = "+ (res.value?.size ?: 0))
            userRepository.saveUser(user)
            val res1 = userRepository.getUserList()
            Log.i(TAG, "after save : user list at DB = "+ (res1.value?.size ?: 0))
        }
    }
}