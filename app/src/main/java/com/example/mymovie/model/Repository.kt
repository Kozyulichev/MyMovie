package com.example.mymovie.model

interface Repository {
    fun getFilmsFromServer(
        language: String,
        callback: retrofit2.Callback<MovieDTO>
    )
    fun getPopularFilmsFromLocaleStorage():List<Film>
    fun getComedyFilmsFromLocaleStorage():List<Film>
}