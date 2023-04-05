package com.nastirlex.cinema.data.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun baseUrl() = "https://react-midterm.kreosoft.space/"

    fun getApiService(baseUrl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    //GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                )
            )
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(
                OkHttpClient.Builder()
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor())
                    .build()
            )
            .build()
            .create(ApiService::class.java)

}