package com.nastirlex.cinema.database.entity

import androidx.room.Entity

@Entity(tableName = "films_table", primaryKeys = ["id", "collectionId"])
data class Film(
    val poster: String,
    val name: String,
    val description: String,
    val collectionId: Long,
    val id: String
)