package com.nastirlex.cinema.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    val name: String,
    @PrimaryKey val id: Long = 0
)