package com.example.mymovie.model.api

import com.example.mymovie.model.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("popular?")
    fun getMovieRetrofit(
        @Query("api_key")token:String,
        @Query("language")language:String,
        @Query("with_genres")id:Int
    ): Call<MovieDTO>
}