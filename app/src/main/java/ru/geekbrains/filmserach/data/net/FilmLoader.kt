package ru.geekbrains.filmserach.data.net

import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import ru.geekbrains.filmserach.data.*
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.domain.SearchOptions

class FilmLoader {

    fun loadFilmsByGenres(): Map<String, List<Film>> {
        val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
        val genres = getAllGenres()
        val field = "genres.name"
        val client = OkHttpClient()

        for (genre in genres) {
            val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$genre"
            val request = Request.Builder().url(url).build()
            val call = client.newCall(request)
            val response = call.execute()

            readResponse(response, genre, filmsByGenresLoaded)
        }

        return filmsByGenresLoaded
    }

    fun loadFilmsBySearchOptions(searchOptions: SearchOptions): List<Film> {
        return listOf()
    }

    private fun readResponse(
        response: Response,
        genre: String,
        filmsByGenresLoaded: MutableMap<String, List<Film>>
    ) {
        if (!response.isSuccessful) {
            return
        }

        response.body().let {
            val filmsJson = it?.string()
            val filmsDto = Gson().fromJson(filmsJson, FilmsDto::class.java)
            val films = FilmConverter.convertListFromDto(filmsDto.films, genre)

            filmsByGenresLoaded[genre] = films
        }
    }
}