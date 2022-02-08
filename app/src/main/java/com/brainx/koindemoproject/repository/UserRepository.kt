package com.brainx.koindemoproject.repository

import com.brainx.koindemoproject.models.User
import com.brainx.koindemoproject.network.ResultWrapper
import com.brainx.koindemoproject.network.SafeAPiCall
import com.brainx.koindemoproject.network.`interface`.ApiService
import com.brainx.koindemoproject.utils.getErrorMessage

class UserRepository(private val apiService: ApiService) {

    suspend fun getUsers(
        response: (List<User>?, String?, Boolean) -> Unit
    ){
        val apiResponse = SafeAPiCall.call {
            apiService.getUsers()
        }
        when (val result = apiResponse) {
            is ResultWrapper.Success -> {
                result.value.apply {
                    if (isSuccessful) {
                        response(
                            body(),
                            message(),
                            isSuccessful,
                        )
                    } else {
                        response(
                            null,
                            getErrorMessage(),
                            isSuccessful,
                        )
                    }
                }
            }
            is ResultWrapper.GenericError -> response(null, result.error?.message, false)
        }
    }
}