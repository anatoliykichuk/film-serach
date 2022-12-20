package ru.geekbrains.filmserach.data

import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.db.FilmEntity
import ru.geekbrains.filmserach.domain.*

class Repository() : Storable {
    private val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
    private val genres = getAllGenres()

    override fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean {
        return filmDatabase.filmDao()
            .isFavorite(film.title, film.originalTitle, film.releaseDate)
    }

    override fun changeFavoritesTag(filmDatabase: FilmDatabase, film: Film) {
        film.isFavorite = !film.isFavorite
        val filmEntity = FilmConverter.convertToEntity(film)

        if (film.isFavorite) {
            filmDatabase.filmDao().insert(filmEntity)
        } else {
            filmDatabase.filmDao().delete(filmEntity)
        }
    }

    override fun getFavorites(filmDatabase: FilmDatabase): List<Film> {
        val filmsEntity: List<FilmEntity> = filmDatabase.filmDao().getFavorites()
        return FilmConverter.convertListFromEntity(filmsEntity)
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
            val films = FilmConverter.convertListFromDto(filmsDto.films, genre)

            filmsByGenresLoaded[genre] = films
        }
    }
}