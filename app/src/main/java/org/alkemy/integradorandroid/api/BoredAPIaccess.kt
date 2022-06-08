package org.alkemy.integradorandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BoredAPIaccess {
    var apiUrl = "http://www.boredapi.com/api/activity"

    // Access API on the 'random activities' endpoint:
    fun getBoredAPI_random(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$apiUrl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Access API on the 'category' endpoint:
    fun getBoredAPI_category(category: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$apiUrl?type=$category")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}