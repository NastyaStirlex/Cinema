package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetCollectionCallback
import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.MovieDto

interface CollectionsRepository {
    fun getCollections(callback: GetCollectionsCallback<List<CollectionDto>>)

    fun createCollection(callback: GetCollectionCallback<CollectionDto>)

    suspend fun deleteCollection(collectionId: String)

    fun getCollectionFilms(collectionId: String, callback: GetMoviesCallback<List<MovieDto>>)

    suspend fun addFilmInCollection(collectionId: String, movieId: String)

    suspend fun deleteFilmFromCollection(collectionId: String, movieId: String)
}