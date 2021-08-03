package com.example.mymovie.model

interface Repository {
    fun getFilmsFromServer():List<Film>
    fun getPopularFilmsFromLocaleStorage():List<Film>
    fun getComedyFilmsFromLocaleStorage():List<Film>
}