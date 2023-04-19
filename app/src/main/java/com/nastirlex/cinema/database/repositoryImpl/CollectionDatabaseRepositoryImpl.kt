package com.nastirlex.cinema.database.repositoryImpl

import android.app.Application
import com.nastirlex.cinema.database.repository.CollectionDatabaseRepository
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.dao.CollectionDao
import com.nastirlex.cinema.database.CollectionDatabase
import com.nastirlex.cinema.database.entity.Film
import com.nastirlex.cinema.database.dao.FilmDao

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

    override suspend fun getFavouritesId(): Long {
        return collectionDao.getFavouritesId()
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

    override suspend fun insertCollectionFilm(film: Film) {
        filmDao.insertFilm(film)
    }

    override suspend fun cleanTable() {
        collectionDao.cleanTable()
    }

}