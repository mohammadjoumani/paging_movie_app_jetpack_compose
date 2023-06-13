package com.mmj.movieapp.domain.repository

import androidx.paging.PagingData
import com.mmj.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<PagingData<Movie>>
}