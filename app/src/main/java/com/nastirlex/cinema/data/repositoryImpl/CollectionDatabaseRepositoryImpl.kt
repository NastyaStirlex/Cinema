package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.content.Context
import com.nastirlex.cinema.data.repository.CollectionDatabaseRepository
import com.nastirlex.cinema.database.Collection
import com.nastirlex.cinema.database.CollectionDao
import com.nastirlex.cinema.database.CollectionDatabase
import com.nastirlex.cinema.database.Film
import com.nastirlex.cinema.database.FilmDao

class CollectionDatabaseRepositoryImpl(application: Application) :
    CollectionDatabaseRepository {

    private var collectionDao: CollectionDao
    private var filmDao: FilmDao

    init {
        val database: CollectionDatabase = CollectionDatabase.getDatabase(application)
        collectionDao = database.collectionDao()
        filmDao = database.filmDao()
    }

    override suspend fun insertCollection(collection: Collection) {
        collectionDao.insertCollection(collection)
    }

    override suspend fun updateCollection(collection: Collection) {
        collectionDao.updateCollection(collection)
    }

    override suspend fun deleteCollection(collectionId: Long) {
        collectionDao.deleteCollection(collectionId)
    }

    override suspend fun getCollections(): List<Collection> {
        return collectionDao.getCollections()
    }

    override suspend fun getCollectionFilms(collectionId: Long): List<Film> {
        return filmDao.getFilms(collectionId)
    }

    override suspend fun cleanTable() {
        collectionDao.cleanTable()
    }

}