package com.nastirlex.cinema.data.repository

import androidx.annotation.WorkerThread
import com.nastirlex.cinema.database.Collection
import com.nastirlex.cinema.database.CollectionDao
import com.nastirlex.cinema.database.Film

interface CollectionDatabaseRepository {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCollection(collection: Collection)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCollection(collection: Collection)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCollection(collectionId: Long)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCollections(): List<Collection>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCollectionFilms(collectionId: Long): List<Film>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun cleanTable()
}