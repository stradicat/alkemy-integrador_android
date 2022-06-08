package org.alkemy.integradorandroid.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class BoredResponse(
    var activity: String,
    var type: String,
    var participants: Int,
    var price: Float,
    var link: Url,
    var key: Int,
    var accessibility: Float
)
