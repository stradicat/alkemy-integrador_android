package org.alkemy.integradorandroid.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface BoredAPI {
    @GET
    suspend fun getBoredFromAPI(@Url query: String): Response<BoredResponse>
}
