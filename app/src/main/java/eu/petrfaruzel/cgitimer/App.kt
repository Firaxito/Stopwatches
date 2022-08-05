package eu.petrfaruzel.cgitimer

import android.app.Application
import android.content.Context

class App: Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance : App

        fun getInstance(): App {
            return instance
        }

        fun getContext() : Context {
            return instance.applicationContext
        }
    }
}