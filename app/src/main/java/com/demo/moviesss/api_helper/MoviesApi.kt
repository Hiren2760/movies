package com.demo.moviesss.api_helper

import com.demo.moviesss.models.MovieDetails
import com.demo.moviesss.models.NowPlayingPojo
import com.demo.moviesss.models.PopularPojo
import com.demo.moviesss.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET(Constants.NOW_PLAYING)
    fun getNowPlayingList(
        @Header("Authorization") token: String,
        @Query("Accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<NowPlayingPojo>

    @GET(Constants.POPULAR)
    fun getPopularList(
        @Header("Authorization") token: String,
        @Query("Accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularPojo>

    @GET(Constants.MOVIE_DETAILS)
    fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("slug", encoded = true) movieId: Int,
        @Query("Accept") accept: String,
        @Query("language") language: String
    ): Call<MovieDetails>
}