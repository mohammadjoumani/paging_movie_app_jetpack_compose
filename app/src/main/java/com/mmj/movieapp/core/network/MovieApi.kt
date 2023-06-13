package com.mmj.movieapp.core.network

import com.mmj.movieapp.core.generic.dto.ResponseDto
import com.mmj.movieapp.data.model.remote.dto.response.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object {
        const val SERVER_URL = "https://api.themoviedb.org/3"
        const val API_URL = "$SERVER_URL/movie/"
    }

    @GET("popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>
}