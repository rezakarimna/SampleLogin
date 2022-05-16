package com.reza.samplelogin.data.models

import com.google.gson.annotations.SerializedName

data class BodyRegister(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = ""
)
