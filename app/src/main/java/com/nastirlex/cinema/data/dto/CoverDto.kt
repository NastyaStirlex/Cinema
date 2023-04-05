package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class CoverDto(
    @SerializedName("backgroundImage")
    var backgroundImage: String,

    @SerializedName("foregroundImage")
    var foregroundImage: String
)