package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.MovieDto

interface MovieRepository {
    fun getCover(callback: GetCoverCallback<CoverDto>)

    fun getInTrend(callback: GetMoviesCallback<MovieDto>)

    fun getLastView(callback: GetMoviesCallback<MovieDto>)

    fun getFresh(callback: GetMoviesCallback<MovieDto>)

    fun getForYou(callback: GetMoviesCallback<MovieDto>)
}