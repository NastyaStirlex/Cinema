package com.nastirlex.cinema.data.dto

data class EpisodeViewDto(
    val episodeId: String,
    val episodeName: String,
    val filePath: String,
    val movieId: String,
    val movieName: String,
    val preview: String,
    val time: Int
)