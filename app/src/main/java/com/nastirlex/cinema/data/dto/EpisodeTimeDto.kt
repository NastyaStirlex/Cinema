package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class EpisodeTimeDto(
    @SerializedName("timeInSeconds")
    val time: Int
)
