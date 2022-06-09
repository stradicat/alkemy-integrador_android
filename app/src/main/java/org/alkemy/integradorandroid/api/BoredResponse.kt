package org.alkemy.integradorandroid.api

data class BoredResponse(

    // API response fields
    var activity: String,
    var type: String,
    var participants: Int,
    var price: Float,
    var link: String,
    var key: Int,
    var accessibility: Float,
    var error: String? = null
)
