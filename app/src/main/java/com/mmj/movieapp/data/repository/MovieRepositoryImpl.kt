package com.mmj.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmj.movieapp.core.app.Constants
import com.mmj.movieapp.data.datasource.remote.MovieRemoteDataSource
import com.mmj.movieapp.data.repository.paging.MoviePagingSource
import com.mmj.movieapp.domain.model.Movie
import com.mmj.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource)
            }
        ).flow
    }
}