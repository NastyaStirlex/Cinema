package com.nastirlex.cinema.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface CollectionDao {
    @Insert
    fun insertCollection(collection: Collection)

    @Delete
    fun deleteCollection(collection: Collection)
}