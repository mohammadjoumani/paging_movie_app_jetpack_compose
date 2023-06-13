package com.mmj.movieapp.domain.usecase

import androidx.paging.PagingData
import com.mmj.movieapp.core.generic.usecase.BaseUseCase
import com.mmj.movieapp.domain.model.Movie
import com.mmj.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Unit, Flow<PagingData<Movie>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}