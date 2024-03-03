package com.demo.moviesss.models

import com.google.gson.annotations.SerializedName

class MovieDetails {

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("id")
    var id: Long = 0L

    @SerializedName("original_language")
    var language: String = ""

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("release_date")
    var releaseDate: String = ""

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("poster_path")
    var posterPath: String = ""

    @SerializedName("tagline")
    var tagline: String = ""

    @SerializedName("vote_average")
    var voteAverage: Float = 0f

    @SerializedName("production_companies")
    var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf()

    @SerializedName("genres")
    var genres: ArrayList<GenresDetails> = arrayListOf()

    @SerializedName("spoken_languages")
    var spokenLanguages: ArrayList<GenresDetails> = arrayListOf()

    inner class SpokenLanguages {
        @SerializedName("english_name")
        var englishName: String = ""

        @SerializedName("iso_639_1")
        var isoCode: String = ""

        @SerializedName("name")
        var name: String = ""
    }

    inner class ProductionCompanies {
        @SerializedName("id")
        var id: String = ""

        @SerializedName("logo_path")
        var logoPath: String = ""

        @SerializedName("name")
        var name: String = ""

        @SerializedName("origin_country")
        var originCountry: String = ""
    }

    inner class GenresDetails {
        @SerializedName("id")
        var id: Int = 0

        @SerializedName("name")
        var name: String = ""
    }
}