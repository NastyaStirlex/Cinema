package com.nastirlex.cinema.data.repositoryImpl

import android.content.Context

class JwtRepositoryImpl() {

    // ACCESS TOKEN

    fun saveAccessToken(context: Context, accessToken: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("accessToken", accessToken)
            apply()
        }
    }

    fun getAccessToken(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("accessToken", null)
    }

    fun deleteAccessToken(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("accessToken", null)
            apply()
        }
    }



    //REFRESH TOKEN

    fun saveRefreshToken(context: Context, refreshToken: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("refreshToken", refreshToken)
            apply()
        }
    }

    fun getRefreshToken(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("refreshToken", null)
    }

    fun deleteRefreshToken(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("refreshToken", null)
            apply()
        }
    }

}