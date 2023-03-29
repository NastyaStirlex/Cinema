package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("accessTokenExpiresIn")
    val accessTokenExpiresIn: Int,

    @SerializedName("refreshToken")
    val refreshToken: String,

    @SerializedName("refreshTokenExpiresIn")
    val refreshTokenExpiresIn: Int,
)