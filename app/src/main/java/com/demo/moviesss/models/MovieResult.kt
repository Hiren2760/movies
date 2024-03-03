package com.demo.moviesss.models

import com.demo.moviesss.api_helper.CallbackStatus

data class MovieResult(val callbackStatus: CallbackStatus, val details: MovieDetails? = null)