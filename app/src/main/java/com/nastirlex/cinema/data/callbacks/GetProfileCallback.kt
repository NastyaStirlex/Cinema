package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.ProfileDto

interface GetProfileCallback<T> {
    fun onSuccess(
        profile: ProfileDto
    )

    fun onError(error: String?)
}