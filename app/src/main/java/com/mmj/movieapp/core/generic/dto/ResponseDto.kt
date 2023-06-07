package com.mmj.movieapp.core.generic.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ResponseDto<T : Any?> {
    @Json(name = "data")
    val data: T? = null

    @Json(name = "message")
    val message: String = ""

    @Json(name = "status")
    val status: Int = 0
}
