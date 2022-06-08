package org.alkemy.integradorandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoredAPIaccess {
    // Access API on the 'random activities' endpoint:
    fun getBoredAPI_randomResponse(): Retrofit {
        val apiUrl = "http://www.boredapi.com/api/activity/"
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}