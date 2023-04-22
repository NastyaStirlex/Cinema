package com.nastirlex.cinema.data.di.service

import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieService {
    @GET("cover")
    fun getCover(): Call<CoverDto>

    @GET("movies?filter=inTrend")
    fun getInTrend(): Call<List<MovieDto>>

    @GET("movies?filter=lastView")
    fun getLastView(): Call<List<MovieDto>>

    @GET("movies?filter=new")
    fun getFresh(): Call<List<MovieDto>>

    @GET("movies?filter=forMe")
    fun getForYou(): Call<List<MovieDto>>

    @GET("history")
    fun getHistory(): Call<List<EpisodeViewDto>>

    @GET("movies?filter=compilation")
    fun getCompilation(): Call<List<MovieDto>>

    @GET("movies/{movieId}/episodes")
    fun getEpisodes(
        @Path("movieId") movieId: String
    ): Call<List<EpisodeDto>>

    @POST("movies/{movieId}/dislike")
    suspend fun deleteFilmFromCompilation(
        @Path("movieId") movieId: String
    ): Response<Void>
}