package com.nastirlex.cinema.data.di

import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.MovieDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {
    @GET("cover")
    fun getCover(@Header("Authorization") token: String): Call<CoverDto>

    @GET("movies?filter=inTrend")
    fun getInTrend(@Header("Authorization") token: String): Call<List<MovieDto>>

    @GET("movies?filter=lastView")
    fun getLastView(@Header("Authorization") token: String): Call<List<MovieDto>>

    @GET("movies?filter=new")
    fun getFresh(@Header("Authorization") token: String): Call<List<MovieDto>>

    @GET("movies?filter=forMe")
    fun getForYou(@Header("Authorization") token: String): Call<List<MovieDto>>
}