package com.twinrock.mymvvm.ui.user_register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twinrock.mymvvm.data.MyResult
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.data.repository.UserRepository
import kotlinx.coroutines.async

class UserRegisterViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {
    val TAG = javaClass.name

    val userList: LiveData<List<User>>  = userRepository.observeUserList().switchMap { switchToRequiredType(it)
    }

    private fun switchToRequiredType(result: MyResult<List<User>>) : LiveData<List<User>> {
        var res = MutableLiveData<List<User>>()
        if (result is MyResult.Success) {
            res.value = result.data
        }
        return res
    }

    fun registerUser(user: User) {
        viewModelScope.async {
//            Log.i(TAG, "registerUser in coroutine----")
            userRepository.saveUser(user)
        }
    }
}