package com.nastirlex.cinema.data.di

import com.nastirlex.cinema.data.dto.CollectionAbbreviateDto
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.MovieDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionsService {
    @GET("collections")
    fun getCollections(@Header("Authorization") token: String): Call<List<CollectionDto>>

    @POST("collections")
    fun createCollection(
        @Header("Authorization") token: String,
        @Body collectionAbbreviateDto: CollectionAbbreviateDto
    ): Call<CollectionDto>

    @DELETE("collections/{collectionId}")
    fun deleteCollection(
        @Header("Authorization") token: String,
        @Path("collectionId") collectionId: String
    ): Response<Unit>

    @GET("collections/{collectionId}/movies")
    fun getCollectionFilms(
        @Header("Authorization") token: String,
        @Path("collectionId") collectionId: String
    ): Call<List<MovieDto>>


    @POST("collections/{collectionId}/movies")
    fun addFilmInCollection(
        @Header("Authorization") token: String,
        @Path("collectionId") collectionId: String,
        @Body movieId: String
    ): Response<Unit>

    @DELETE("collections/{collectionId}/movies")
    fun deleteFilmFromCollection(
        @Header("Authorization") token: String,
        @Path("collectionId") collectionId: String,
        @Query("movieId") movieId: String
    ): Response<Unit>
}