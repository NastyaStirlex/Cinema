package com.nastirlex.cinema.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CollectionDao {
    @Insert
    fun insertCollection(collection: Collection)

    @Update
    fun updateCollection(collection: Collection)

    @Query("DELETE FROM collections_table WHERE id = :collectionId")
    fun deleteCollection(collectionId: Long)

    @Query("SELECT * FROM collections_table")
    fun getCollections(): List<Collection>

    @Query("DELETE FROM collections_table")
    fun cleanTable()
}