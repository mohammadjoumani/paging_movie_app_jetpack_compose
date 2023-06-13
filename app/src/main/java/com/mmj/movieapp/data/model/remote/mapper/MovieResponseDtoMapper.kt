package com.mmj.movieapp.data.model.remote.mapper

import com.mmj.movieapp.data.model.remote.dto.response.MovieResponseDto
import com.mmj.movieapp.domain.model.Movie

fun MovieResponseDto.mapFromEntity() = Movie(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun Movie.mapFromDomain() = MovieResponseDto(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<MovieResponseDto>.mapFromListModel(): List<Movie> {
    return this.map {
        it.mapFromEntity()
    }
}

fun List<Movie>.mapFromListDomain(): List<MovieResponseDto> {
    return this.map {
        it.mapFromDomain()
    }
}