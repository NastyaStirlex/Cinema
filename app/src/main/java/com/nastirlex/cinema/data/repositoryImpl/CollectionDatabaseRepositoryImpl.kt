package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.content.Context
import com.nastirlex.cinema.data.repository.CollectionDatabaseRepository
import com.nastirlex.cinema.database.Collection
import com.nastirlex.cinema.database.CollectionDao
import com.nastirlex.cinema.database.CollectionDatabase

class CollectionDatabaseRepositoryImpl(application: Application) :
    CollectionDatabaseRepository {

    private var collectionDao: CollectionDao

    init {
        val database: CollectionDatabase = CollectionDatabase.getDatabase(application)
        collectionDao = database.collectionDao()
    }

    override suspend fun insertCollection(collection: Collection) {
        collectionDao.insertCollection(collection)
    }

    override suspend fun deleteCollection(collection: Collection) {
        collectionDao.deleteCollection(collection)
    }

}