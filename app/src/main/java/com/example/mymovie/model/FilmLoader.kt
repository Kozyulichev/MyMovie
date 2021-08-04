package com.example.mymovie.model

class FilmLoader()

interface FilmLoadListener{
    fun onLoad()
    fun onFailed()
}
