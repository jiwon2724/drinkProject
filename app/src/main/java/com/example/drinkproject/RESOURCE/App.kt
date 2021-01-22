package com.example.drinkproject.RESOURCE

import android.app.Application

class App : Application() {
    companion object {
        lateinit var prefs : DrinkSharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        prefs = DrinkSharedPreferences(applicationContext)
    }
}