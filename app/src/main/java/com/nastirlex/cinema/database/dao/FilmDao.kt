package com.nastirlex.cinema.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nastirlex.cinema.database.entity.Film

@Dao
interface FilmDao {
    @Insert
    fun insertFilm(film: Film)

    @Query("DELETE FROM films_table WHERE id = :movieId AND collectionId = :collectionId")
    fun deleteFilm(movieId: String, collectionId: Long)

    @Query("SELECT * FROM films_table WHERE collectionId = :collectionId")
    fun getFilms(collectionId: Long): List<Film>

    @Query("SELECT name FROM films_table WHERE id = :movieId AND collectionId = :collectionId")
    fun isFilmInCollection(movieId: String, collectionId: Long): String?

    @Query("DELETE FROM films_table")
    fun cleanTable()
}