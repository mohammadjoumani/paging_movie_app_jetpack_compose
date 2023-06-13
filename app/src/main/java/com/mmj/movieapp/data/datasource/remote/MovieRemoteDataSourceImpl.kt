package com.mmj.movieapp.data.datasource.remote

import com.mmj.movieapp.core.generic.dto.ResponseDto
import com.mmj.movieapp.core.network.MovieApi
import com.mmj.movieapp.data.model.remote.dto.response.MovieResponseDto
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MovieApi
) : MovieRemoteDataSource {

    override suspend fun getMovies(
        apiKey: String,
        pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>> {
        return api.getMovies(apiKey = apiKey, pageNumber = pageNumber)
    }

}