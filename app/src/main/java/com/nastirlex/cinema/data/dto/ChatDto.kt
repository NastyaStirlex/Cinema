package com.nastirlex.cinema.data.dto

data class ChatDto(
    val chatId: String,
    val chatName: String,
    val messageDto: MessageDto
)