package com.example.mymovie.viewModel

import com.example.mymovie.model.MovieDTO

sealed class AppState {
    data class Success(val popularFilmData: MovieDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}