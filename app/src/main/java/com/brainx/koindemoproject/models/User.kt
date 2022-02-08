package com.brainx.koindemoproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id: Int = 0,
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("email")
    @Expose
    val email: String = "",
    @SerializedName("avatar")
    @Expose
    val avatar: String = ""
)
