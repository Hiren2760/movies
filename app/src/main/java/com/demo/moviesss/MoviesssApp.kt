package com.demo.moviesss

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication

class MoviesssApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        private var mInstance: MoviesssApp? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): MoviesssApp {
            return mInstance ?: MoviesssApp()
        }
    }
}