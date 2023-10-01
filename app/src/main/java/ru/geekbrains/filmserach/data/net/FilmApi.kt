package ru.geekbrains.filmserach.data.net

import com.example.example.FilmsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

const val END_POINT = "v1/movie"

interface FilmApi {
    @GET(END_POINT)
    fun getByGenre(
        @Header("X-API-KEY") token: String,
        @Query("field") genreKey: String,
        @Query("search") genreValue: String,
        @Query("selectFields") selectFields: String
    ): Call<FilmsDto>

    @GET
    fun getBySearchOptions(
        @Url url: String
    ): Call<FilmsDto>
}