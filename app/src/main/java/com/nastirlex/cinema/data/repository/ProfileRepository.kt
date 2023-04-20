package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetProfileCallback
import com.nastirlex.cinema.data.dto.ProfileDto

interface ProfileRepository {
    fun getProfile(callback: GetProfileCallback<ProfileDto>)
}