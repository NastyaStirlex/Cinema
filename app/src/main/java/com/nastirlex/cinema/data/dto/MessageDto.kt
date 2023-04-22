package com.nastirlex.cinema.data.dto

data class MessageDto(
    val authorAvatar: String?,
    val authorId: String,
    val authorName: String,
    val creationDateTime: String,
    val messageId: String,
    val text: String
)