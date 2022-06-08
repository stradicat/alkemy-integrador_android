package org.alkemy.integradorandroid.api

import com.google.gson.annotations.SerializedName

data class BoredResponse(
    @SerializedName("names")
    var boredAPIList: List<String>
)
