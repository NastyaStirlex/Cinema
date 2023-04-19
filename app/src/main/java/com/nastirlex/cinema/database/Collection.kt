package com.nastirlex.cinema.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "collections_table", indices = [Index(value = ["name"], unique = true)])
data class Collection(
    val name: String,
    val icon: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)