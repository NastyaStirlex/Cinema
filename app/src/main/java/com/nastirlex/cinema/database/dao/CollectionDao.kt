package com.nastirlex.cinema.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nastirlex.cinema.database.entity.Collection

@Dao
interface CollectionDao {
    @Insert
    fun insertCollection(collection: Collection)

    @Update
    fun updateCollection(collection: Collection)

    @Query("SELECT id FROM collections_table WHERE name = 'Favourites'")
    fun getFavouritesId(): Long

    @Query("DELETE FROM collections_table WHERE id = :collectionId")
    fun deleteCollection(collectionId: Long)

    @Query("SELECT * FROM collections_table")
    fun getCollections(): List<Collection>

    @Query("DELETE FROM collections_table")
    fun cleanTable()
}