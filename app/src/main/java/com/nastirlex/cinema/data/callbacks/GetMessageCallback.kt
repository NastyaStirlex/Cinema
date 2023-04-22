package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.MessageDto

interface GetMessageCallback<T> {
    fun onSuccess(
        message: MessageDto
    )

    fun onError(error: String?)
}