package com.brainx.koindemoproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    @Expose
    var error: String? = null
)