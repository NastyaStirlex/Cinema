package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class RegisterBodyDto(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("firstName")
    var firstName: String,

    @SerializedName("lastName")
    var lastName: String
)
