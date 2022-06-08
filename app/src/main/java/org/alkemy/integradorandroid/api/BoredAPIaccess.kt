package org.alkemy.integradorandroid.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BoredAPIaccess {

    // Access API on the 'random activities' endpoint:
    fun getBoredAPI_random(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$BASE_URL/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Access API on the 'category' endpoint:
    fun getBoredAPI_category(category: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$BASE_URL?type=$category")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


 /*   val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }*/

    companion object{
        const val BASE_URL = "http://www.boredapi.com/api/activity"
    }

}