package com.example.mymovie

sealed class AppState {
    data class Success(val popularFilmData: List<Film>,val comedyFilmData:List<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}