package org.alkemy.integradorandroid.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class BoredResponse(
    @SerializedName("activity")
    var activity: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("participants")
    var participants: Int,
    @SerializedName("price")
    var price: Float,
    @SerializedName("link")
    var link: Url,
    @SerializedName("key")
    var key: Int,
    @SerializedName("accesibility")
    var accessibility: Float
)
