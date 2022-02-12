package com.brainx.koindemoproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.koindemoproject.repository.UserRepository
import com.brainx.koindemoproject.ui.MainActivity
import com.brainx.koindemoproject.viewIntent.MainActivityIntent
import com.brainx.koindemoproject.viewState.MainActivityState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(private val userRepository: UserRepository):ViewModel() {
    val userIntent = Channel<MainActivityIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Idle)
    val state: StateFlow<MainActivityState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainActivityIntent.FetchUsers -> getUsers()
                }
            }
        }
    }

   private fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MainActivityState.Loading
            userRepository.getUsers(){data,message,status->
                if (status)
                    _state.value = MainActivityState.Users(data)
                else
                    _state.value = MainActivityState.Error(message)
            }
        }
    }
}