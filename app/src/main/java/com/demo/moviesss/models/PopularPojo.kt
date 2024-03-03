package com.demo.moviesss.models

import com.google.gson.annotations.SerializedName

class PopularPojo {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("results")
    var results: ArrayList<PopularResults> = arrayListOf()

    inner class PopularResults {
        @SerializedName("id")
        var id: Long = 0L

        @SerializedName("original_language")
        var language: String = ""

        @SerializedName("original_title")
        var originalTitle: String = ""

        @SerializedName("overview")
        var description: String = ""

        @SerializedName("release_date")
        var releaseDate: String = ""

        @SerializedName("title")
        var title: String = ""

        @SerializedName("adult")
        var isAdult: Boolean = false

        @SerializedName("poster_path")
        var posterPath: String = ""

        @SerializedName("vote_average")
        var voteAverage: Float = 0f
    }
}