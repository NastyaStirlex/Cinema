package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.callbacks.GetHistoryCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

class MovieRepositoryImpl(private val application: Application) : MovieRepository {

    private var callGetCover: Call<CoverDto>? = null

    override fun getCover(callback: GetCoverCallback<CoverDto>) {
        callGetCover = ApiClient.getMovieApiService(application.applicationContext)
            .getCover()
        callGetCover?.enqueue(
            object : Callback<CoverDto> {
                override fun onResponse(call: Call<CoverDto>, response: Response<CoverDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
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
        callGetInTrend = ApiClient.getMovieApiService(application.applicationContext).getInTrend()
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
                        if (response.isSuccessful) {
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
        callGetLastView = ApiClient.getMovieApiService(application.applicationContext).getLastView()
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
                        if (response.isSuccessful) {
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
        callGetFresh = ApiClient.getMovieApiService(application.applicationContext).getFresh()
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
                        if (response.isSuccessful) {
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
        callGetForYou = ApiClient.getMovieApiService(application.applicationContext).getForYou()
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
                        if (response.isSuccessful) {
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

    private var callGetHistory: Call<List<EpisodeViewDto>>? = null
    override fun getHistory(callback: GetHistoryCallback<List<EpisodeViewDto>>) {
        callGetHistory = ApiClient.getMovieApiService(application.applicationContext).getHistory()
        callGetHistory?.enqueue(
            object : Callback<List<EpisodeViewDto>> {
                override fun onResponse(
                    call: Call<List<EpisodeViewDto>>,
                    response: Response<List<EpisodeViewDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                episodesView = it
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

                override fun onFailure(call: Call<List<EpisodeViewDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    private var callGetCompilation: Call<List<MovieDto>>? = null
    override fun getCompilation(callback: GetMoviesCallback<MovieDto>) {
        callGetCompilation =
            ApiClient.getMovieApiService(application.applicationContext).getCompilation()
        callGetCompilation?.enqueue(
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
                        if (response.isSuccessful) {
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

    private var callGetEpisodes: Call<List<EpisodeDto>>? = null
    override fun getEpisodes(movieId: String, callback: GetEpisodesCallback<List<EpisodeDto>>) {
        callGetEpisodes = ApiClient.getMovieApiService(application.applicationContext).getEpisodes(
            movieId = movieId
        )
        callGetEpisodes?.enqueue(
            object : Callback<List<EpisodeDto>> {
                override fun onResponse(
                    call: Call<List<EpisodeDto>>,
                    response: Response<List<EpisodeDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                episodes = it
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

                override fun onFailure(call: Call<List<EpisodeDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    override suspend fun deleteFilmFromCompilation(movieId: String) {
        try {
            ApiClient.getMovieApiService(application.applicationContext).deleteFilmFromCompilation(
                movieId = movieId
            )
        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //profileScreenState.value = Event.error(R.string.http_error)
                }

                is UnknownHostException, is SocketException -> {
                    //profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
        }
    }
}