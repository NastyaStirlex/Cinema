package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.ChatInfoDto

interface GetChatInfoCallback<T> {
    fun onSuccess(
        chatInfo: ChatInfoDto
    )

    fun onError(error: String?)
}