package ru.geekbrains.filmserach.data.net

import com.example.example.FilmsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.filmserach.data.END_POINT

interface FilmApi {
    @GET(END_POINT)
    fun getByGenre(
        @Query("token") token: String,
        @Query("field") genreKey: String,
        @Query("search") genreValue: String
    ): Call<FilmsDto>

    @GET(END_POINT)
    fun getBySearchOptions(
        @Query("token") token: String,
        @Query("field") nameKey: String,
        @Query("search") nameValue: String,
        @Query("field") genreKey: String,
        @Query("search") genreValue: String,
        @Query("field") countryKey: String,
        @Query("search") countryValue: String,
        @Query("field") yearKey: String,
        @Query("search") yearValue: String,
        @Query("field") popularityKey: String,
        @Query("search") popularityValue: String
    ): Call<FilmsDto>
}