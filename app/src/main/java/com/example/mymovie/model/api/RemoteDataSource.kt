package com.example.mymovie.model.api

import com.example.mymovie.model.MovieDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "eaa99c6fd63b5488a4816703a57c78f7"

class RemoteDataSource {
    private val movieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieAPI::class.java)

    fun getMovieDetails(language: String, callback: Callback<MovieDTO>) {
        movieAPI.getMovieRetrofit(API_KEY, language).enqueue(callback)
    }
}