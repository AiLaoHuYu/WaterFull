package com.example.mylearningdemo

import android.app.Application

class App : Application() {

    private lateinit var mInstance: App


    fun getInstance(): App {
        if (mInstance == null) {
            synchronized(App::class.java) {
                mInstance = App()
                return mInstance
            }
        }
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }


}