package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.CollectionDto

interface GetCollectionCallback<T> {
    fun onSuccess(
        collection: CollectionDto
    )

    fun onError(error: String?)
}