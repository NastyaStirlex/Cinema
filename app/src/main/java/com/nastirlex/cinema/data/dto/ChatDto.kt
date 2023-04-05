package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class ChatDto(
    @SerializedName("chatId")
    val chatId: String,

    @SerializedName("chatName")
    val chatName: String
)