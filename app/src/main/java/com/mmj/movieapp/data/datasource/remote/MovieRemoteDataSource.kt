package com.mmj.movieapp.data.datasource.remote

import com.mmj.movieapp.core.generic.dto.ResponseDto
import com.mmj.movieapp.data.model.remote.dto.response.MovieResponseDto

interface MovieRemoteDataSource {

    suspend fun getMovies(
        apiKey: String,
        pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>

}
