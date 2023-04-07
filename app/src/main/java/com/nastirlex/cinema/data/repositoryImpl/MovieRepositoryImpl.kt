package com.nastirlex.cinema.data.repositoryImpl

import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRepositoryImpl: MovieRepository {
    private var callGetCover: Call<CoverDto>? = null

    private var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMDc2ODQud2ViLmhvc3RpbmctcnVzc2lhLnJ1IiwiZXhwIjoxNjgwNjg2NzkwLCJLRVlfQ0xBSU1fVVNFUiI6ImViZTgwOTg2LTA4ZmQtNDE1Yi1hNzk0LWYyYWIwOTAwOTdkMCJ9.mAZaKxyg6TIrf4JlghHLFtIJqQLe6r4D_C5U5Ba-BiQ"

    override fun getCover(callback: GetCoverCallback<CoverDto>) {
        callGetCover = ApiClient.movieApiService.getCover(token = "Bearer $token")
        callGetCover?.enqueue(
            object : Callback<CoverDto> {
                override fun onResponse(call: Call<CoverDto>, response: Response<CoverDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                cover = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
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

                override fun onFailure(call: Call<CoverDto>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }

    private var callGetInTrend: Call<List<MovieDto>>? = null
    override fun getInTrend(callback: GetMoviesCallback<MovieDto>) {
        callGetInTrend = ApiClient.movieApiService.getInTrend(token = "Bearer $token")
        callGetInTrend?.enqueue(
            object : Callback<List<MovieDto>> {
                override fun onResponse(
                    call: Call<List<MovieDto>>,
                    response: Response<List<MovieDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                movies = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
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

                override fun onFailure(call: Call<List<MovieDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )


    }

    private var callGetLastView: Call<List<MovieDto>>? = null
    override fun getLastView(callback: GetMoviesCallback<MovieDto>) {
        callGetLastView = ApiClient.movieApiService.getLastView(token = "Bearer $token")
        callGetLastView?.enqueue(
            object : Callback<List<MovieDto>> {
                override fun onResponse(
                    call: Call<List<MovieDto>>,
                    response: Response<List<MovieDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                movies = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
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

                override fun onFailure(call: Call<List<MovieDto>>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )

    }

    private var callGetFresh: Call<List<MovieDto>>? = null
    override fun getFresh(callback: GetMoviesCallback<MovieDto>) {
        callGetFresh = ApiClient.movieApiService.getFresh(token = "Bearer $token")
        callGetFresh?.enqueue(
            object : Callback<List<MovieDto>> {
                override fun onResponse(
                    call: Call<List<MovieDto>>,
                    response: Response<List<MovieDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                movies = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
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

                override fun onFailure(call: Call<List<MovieDto>>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }

    private var callGetForYou: Call<List<MovieDto>>? = null
    override fun getForYou(callback: GetMoviesCallback<MovieDto>) {
        callGetForYou = ApiClient.movieApiService.getForYou(token = "Bearer $token")
        callGetForYou?.enqueue(
            object : Callback<List<MovieDto>> {
                override fun onResponse(
                    call: Call<List<MovieDto>>,
                    response: Response<List<MovieDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if(response.isSuccessful) {
                            callback.onSuccess(
                                movies = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
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

                override fun onFailure(call: Call<List<MovieDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}