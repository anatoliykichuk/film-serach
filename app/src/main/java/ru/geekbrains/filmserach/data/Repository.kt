package ru.geekbrains.filmserach.data

import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import ru.geekbrains.filmserach.domain.*

class Repository() : Storable {
    private val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
    private val genres = getAllGenres()

    override fun getFilmsByGenresFromDb(): Map<String, List<Film>> {
        TODO("Not yet implemented")
    }

    override fun getFilmsByGenresFromNet(): Map<String, List<Film>> {
        val field = "genres.name"
        val client = OkHttpClient()
        doRequest(client, field)

        return filmsByGenresLoaded
    }

    private fun doRequest(client: OkHttpClient, field: String) {
        for (genre in genres) {
            val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$genre"
            val request = Request.Builder().url(url).build()
            val call = client.newCall(request)
            val response = call.execute()

            readResponse(response, genre)
        }
    }

    private fun readResponse(response: Response, genre: String) {
        if (!response.isSuccessful) {
            return
        }

        response.body().let {
            val filmsJson = it?.string()
            val filmsDto = Gson().fromJson(filmsJson, FilmsDto::class.java)
            val films = FilmConverter.convertListFromDto(filmsDto.films)

            filmsByGenresLoaded[genre] = films
        }
    }
}