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
        @Query("field") field: String,
        @Query("search") search: String,
    ): Call<FilmsDto>
}