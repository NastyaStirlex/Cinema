package com.nastirlex.cinema.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nastirlex.cinema.database.dao.CollectionDao
import com.nastirlex.cinema.database.dao.FilmDao
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.entity.Film

@Database(entities = [Collection::class, Film::class], version = 1)
abstract class CollectionDatabase: RoomDatabase() {
    abstract fun collectionDao(): CollectionDao

    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: CollectionDatabase? = null

        fun getDatabase(application: Application): CollectionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    CollectionDatabase::class.java,
                    "collection_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}