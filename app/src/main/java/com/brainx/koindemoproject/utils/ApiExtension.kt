package com.brainx.koindemoproject.utils

import com.brainx.koindemoproject.models.Error
import retrofit2.Response


fun <T> Response<T>.getErrorMessage(): String {
    val error = errorBody()?.string()?.toObject(Error::class.java) as? Error
    return error?.error ?: message()

}