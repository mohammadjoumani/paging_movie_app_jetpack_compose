package com.mmj.movieapp.core.generic.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class ResponseDto<T : Any?> {
    @SerializedName("results")
    val results: T? = null

    @SerializedName("page")
    val page: Int? = null

    @SerializedName("total_pages")
    val totalPages: Int? = null

    @SerializedName("total_results")
    val totalResults: Int? = null
}