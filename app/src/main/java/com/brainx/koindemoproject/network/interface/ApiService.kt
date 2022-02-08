package com.brainx.koindemoproject.network.`interface`

import com.brainx.koindemoproject.models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}