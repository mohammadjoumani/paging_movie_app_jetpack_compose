package com.mmj.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mmj.movieapp.domain.model.Movie
import com.mmj.movieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

    init {
        onEvent(HomeEvent.GetHome)
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetHome -> {
                    getMovies()
                }
            }
        }
    }

    private suspend fun getMovies() {
        getMoviesUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }
}

sealed class HomeEvent {
    object GetHome : HomeEvent()
}

data class HomeState(
    val movies: List<Movie> = listOf()
)