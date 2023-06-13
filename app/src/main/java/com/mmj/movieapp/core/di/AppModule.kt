package com.mmj.movieapp.core.di

import com.mmj.movieapp.core.network.MovieApi
import com.mmj.movieapp.data.datasource.remote.MovieRemoteDataSource
import com.mmj.movieapp.data.datasource.remote.MovieRemoteDataSourceImpl
import com.mmj.movieapp.data.repository.MovieRepositoryImpl
import com.mmj.movieapp.domain.repository.MovieRepository
import com.mmj.movieapp.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: MovieApi
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesGetMoviesUseCase(
        movieRepository: MovieRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }
}