package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetProfileCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.ProfileDto
import com.nastirlex.cinema.data.repository.ProfileRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ProfileRepositoryImpl(private val application: Application): ProfileRepository {

    private var callGetProfile: Call<ProfileDto>? = null
    override fun getProfile(callback: GetProfileCallback<ProfileDto>) {
        callGetProfile = ApiClient.getProfileApiService(application.applicationContext).getProfile()
        callGetProfile?.enqueue(
            object :Callback<ProfileDto> {
                override fun onResponse(call: Call<ProfileDto>, response: Response<ProfileDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let {
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                profile = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<ProfileDto>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }
}