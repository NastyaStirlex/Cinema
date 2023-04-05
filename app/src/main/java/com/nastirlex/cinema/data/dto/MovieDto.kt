package com.nastirlex.cinema.data.dto

data class MovieDto(
    val age: String,
    val chatDto: List<ChatDto>,
    val description: String,
    val imageUrls: List<String>,
    val movieId: String,
    val name: String,
    val poster: String,
    val tagDtos: List<TagDto>
)