package com.demo.moviesss.models

import com.demo.moviesss.api_helper.CallbackStatus

data class ApiResult<T>(val callbackStatus: CallbackStatus, val list: ArrayList<T> = arrayListOf())