package com.nastirlex.cinema.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {
    @Insert
    fun insertFilm(film: Film)


    @Delete
    fun deleteFilm(film: Film)

    @Query("SELECT * FROM films_table WHERE collectionId = :collectionId")
    fun getFilms(collectionId: Long): List<Film>
}