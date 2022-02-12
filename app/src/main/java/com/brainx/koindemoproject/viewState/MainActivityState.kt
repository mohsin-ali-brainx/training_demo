package com.brainx.koindemoproject.viewState

import com.brainx.koindemoproject.models.User

sealed class MainActivityState{
    object Idle : MainActivityState()
    object Loading : MainActivityState()
    data class Users(val user: List<User>?) : MainActivityState()
    data class Error(val error: String?) : MainActivityState()
}
