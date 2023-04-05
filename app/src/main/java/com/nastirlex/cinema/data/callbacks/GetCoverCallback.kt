package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.CoverDto

interface GetCoverCallback<T> {
    fun onSuccess(
        cover: CoverDto
    )

    fun onError(error: String?)
}