package com.brainx.koindemoproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.koindemoproject.models.User
import com.brainx.koindemoproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository):ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        getUsers()
    }

   private fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers(){data,message,status->
                if (status)
                    _users.postValue(data)
            }
        }
    }
}