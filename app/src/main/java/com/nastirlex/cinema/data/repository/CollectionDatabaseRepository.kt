package com.nastirlex.cinema.data.repository

import androidx.annotation.WorkerThread
import com.nastirlex.cinema.database.Collection
import com.nastirlex.cinema.database.CollectionDao

interface CollectionDatabaseRepository {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCollection(collection: Collection)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCollection(collection: Collection)
}