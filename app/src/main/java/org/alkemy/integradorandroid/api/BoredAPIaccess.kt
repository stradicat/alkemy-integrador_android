package org.alkemy.integradorandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoredAPIaccess {

    private val baseURL = "http://www.boredapi.com/api/activity"

    // Access API on the 'random activities' endpoint:
    fun getBoredAP(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${baseURL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}