package com.nastirlex.cinema.data.di

import com.nastirlex.cinema.data.dto.ProfileDto
import retrofit2.Call
import retrofit2.http.GET

interface ProfileService {
    @GET("profile")
    fun getProfile(): Call<ProfileDto>
}