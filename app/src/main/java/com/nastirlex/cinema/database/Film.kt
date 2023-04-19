package com.nastirlex.cinema.database

import androidx.room.Entity

@Entity(tableName = "films_table", primaryKeys = ["id", "collectionId"])
data class Film(
    val poster: Int,
    val name: String,
    val description: String,
    val collectionId: Long,
    val id: String
)