package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.dto.CollectionDto

interface CollectionsRepository {
    fun getCollections(callback: GetCollectionsCallback<List<CollectionDto>>)
}