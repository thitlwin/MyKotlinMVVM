package com.twinrock.mymvvm.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository

class UserViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {
    val userList: LiveData<List<User>> = userRepository.getUserList()
}