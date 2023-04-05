package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.CoverDto

interface GetCollectionsCallback<T> {
    fun onSuccess(
        collections: List<CollectionDto>
    )

    fun onError(error: String?)
}