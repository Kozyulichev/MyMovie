package com.example.mymovie.model


class RepositoryImpl : Repository {

    override fun getFilmsFromServer(): List<Film> {
        return getPopularFilms()
    }

    override fun getPopularFilmsFromLocaleStorage() = getPopularFilms()

    override fun getComedyFilmsFromLocaleStorage() = getComedyFilms()


}