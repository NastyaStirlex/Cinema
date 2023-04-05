package com.nastirlex.cinema.data.dto

import com.google.gson.annotations.SerializedName

data class CollectionDto(
    @SerializedName("collectionId")
    var collectionId: String,

    @SerializedName("name")
    var name: String,
)
