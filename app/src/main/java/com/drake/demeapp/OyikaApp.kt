package com.drake.demeapp

import android.app.Application
import android.content.Context

class OyikaApp : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: OyikaApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}
