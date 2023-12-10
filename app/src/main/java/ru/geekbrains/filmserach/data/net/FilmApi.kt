package ru.geekbrains.filmserach.data.net

import com.example.example.FilmsDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

const val END_POINT = "v1.4/movie"
const val END_POINT_FOR_SEARCH_BY_NAME = "v1.4/movie/search"

interface FilmApi {
    @GET(END_POINT)
    fun getByGenre(
        @Header("X-API-KEY") token: String,
        @Query("genres.name") genreValue: String,
        @Query("selectFields") selectFields: Array<String>
    ): Deferred<FilmsDto>

    @GET
    fun getBySearchOptions(
        @Header("X-API-KEY") token: String,
        @Url url: String
    ): Deferred<FilmsDto>
}