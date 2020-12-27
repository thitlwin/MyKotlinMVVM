package com.twinrock.mymvvm.ui.user_edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserEditViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user.id.toString())
        }
    }
}