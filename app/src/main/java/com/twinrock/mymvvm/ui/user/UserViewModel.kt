package com.twinrock.mymvvm.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twinrock.mymvvm.data.MyResult
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository

class UserViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _userList: LiveData<List<User>> = userRepository.observeUserList().switchMap { switchToRequiredType(it) }
    val userList: LiveData<List<User>> = _userList


    private fun switchToRequiredType(it: MyResult<List<User>>): LiveData<List<User>> {
        var res = MutableLiveData<List<User>>()
        if (it is MyResult.Success) {
            res.value = it.data
        }
        return res
    }
}