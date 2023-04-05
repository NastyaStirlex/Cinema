package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.MovieDto

interface GetMoviesCallback<T> {
    fun onSuccess(
        movies: List<MovieDto>
    )

    fun onError(error: String?)
}