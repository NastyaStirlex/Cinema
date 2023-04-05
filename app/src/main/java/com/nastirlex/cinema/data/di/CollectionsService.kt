package com.nastirlex.cinema.data.di

import com.nastirlex.cinema.data.dto.CollectionDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CollectionsService {
    @GET("collections")
    fun getCollections(@Header("Authorization") token: String): Call<List<CollectionDto>>
}