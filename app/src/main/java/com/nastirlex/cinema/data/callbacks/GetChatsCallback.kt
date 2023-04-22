package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.data.dto.CollectionDto

interface GetChatsCallback<T> {
    fun onSuccess(
        chats: List<ChatDto>
    )

    fun onError(error: String?)
}