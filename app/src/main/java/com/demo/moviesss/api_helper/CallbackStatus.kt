package com.demo.moviesss.api_helper

sealed class CallbackStatus{
    object IDLE : CallbackStatus()
    object LOADING : CallbackStatus()
    object SUCCESS : CallbackStatus()
}