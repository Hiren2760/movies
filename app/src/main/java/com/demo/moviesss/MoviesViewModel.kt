package com.demo.moviesss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.moviesss.api_helper.CallbackStatus
import com.demo.moviesss.api_helper.MoviesApi
import com.demo.moviesss.api_helper.RetrofitInstance
import com.demo.moviesss.models.ApiResult
import com.demo.moviesss.models.MovieDetails
import com.demo.moviesss.models.MovieResult
import com.demo.moviesss.models.NowPlayingPojo
import com.demo.moviesss.models.PopularPojo
import com.demo.moviesss.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    private var moviesApi: MoviesApi = RetrofitInstance.getInstance().create(MoviesApi::class.java)
    private var _nowPlayingList: MutableLiveData<ApiResult<NowPlayingPojo.NowPlayingResults>> =
        MutableLiveData()
    val nowPlayingList: LiveData<ApiResult<NowPlayingPojo.NowPlayingResults>> get() = _nowPlayingList

    private var _popularList: MutableLiveData<ApiResult<PopularPojo.PopularResults>> =
        MutableLiveData()
    val popularList: LiveData<ApiResult<PopularPojo.PopularResults>> get() = _popularList

    private var _movieDetails: MutableLiveData<MovieResult> = MutableLiveData()
    val movieDetails: LiveData<MovieResult> get() = _movieDetails

    fun loadNowPlaying() {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingList.postValue(
                ApiResult(
                    CallbackStatus.LOADING
                )
            )

            moviesApi.getNowPlayingList(
                accept = "application/json",
                token = "Bearer ${Constants.BEARER_TOKEN}",
                language = "en-US",
                page = 1
            ).enqueue(object : Callback<NowPlayingPojo> {
                override fun onResponse(
                    call: Call<NowPlayingPojo>,
                    response: Response<NowPlayingPojo>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _nowPlayingList.postValue(
                                ApiResult(
                                    CallbackStatus.SUCCESS,
                                    it.results
                                )
                            )
                        } ?: _nowPlayingList.postValue(
                            ApiResult(
                                CallbackStatus.SUCCESS
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<NowPlayingPojo>, t: Throwable) {
                    _nowPlayingList.postValue(
                        ApiResult(
                            CallbackStatus.SUCCESS
                        )
                    )
                }
            })
        }
    }

    fun loadPopular() {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingList.postValue(
                ApiResult(
                    CallbackStatus.LOADING
                )
            )

            moviesApi.getPopularList(
                accept = "application/json",
                token = "Bearer ${Constants.BEARER_TOKEN}",
                language = "en-US",
                page = 1
            ).enqueue(object : Callback<PopularPojo> {
                override fun onResponse(
                    call: Call<PopularPojo>,
                    response: Response<PopularPojo>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _popularList.postValue(
                                ApiResult(
                                    CallbackStatus.SUCCESS,
                                    it.results
                                )
                            )
                        } ?: _popularList.postValue(
                            ApiResult(
                                CallbackStatus.SUCCESS
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<PopularPojo>, t: Throwable) {
                    _popularList.postValue(
                        ApiResult(
                            CallbackStatus.SUCCESS
                        )
                    )
                }
            })
        }
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingList.postValue(
                ApiResult(
                    CallbackStatus.LOADING
                )
            )

            moviesApi.getMovieDetails(
                accept = "application/json",
                token = "Bearer ${Constants.BEARER_TOKEN}",
                language = "en-US",
                movieId = movieId
            ).enqueue(object : Callback<MovieDetails> {
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _movieDetails.postValue(
                                MovieResult(
                                    CallbackStatus.SUCCESS,
                                    it
                                )
                            )
                        } ?: _movieDetails.postValue(
                            MovieResult(
                                CallbackStatus.SUCCESS
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    _movieDetails.postValue(
                        MovieResult(
                            CallbackStatus.SUCCESS
                        )
                    )
                }
            })
        }
    }
}