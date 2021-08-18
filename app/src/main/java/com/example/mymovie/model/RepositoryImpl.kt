package com.example.mymovie.model

import com.example.mymovie.model.api.RemoteDataSource
import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getFilmsFromServer(language: String, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(language,callback)
    }
    override fun getPopularFilmsFromLocaleStorage() = getPopularFilms()

    override fun getComedyFilmsFromLocaleStorage() = getComedyFilms()


}