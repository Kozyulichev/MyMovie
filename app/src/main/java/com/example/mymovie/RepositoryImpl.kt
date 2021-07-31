package com.example.mymovie


class RepositoryImpl : Repository {

    override fun getFilmsFromServer(): List<Film> {
        return getPopularFilms()
    }

    override fun getPopularFilmsFromLocaleStorage() = getPopularFilms()

    override fun getComedyFilmsFromLocaleStorage() = getComedyFilms()


}