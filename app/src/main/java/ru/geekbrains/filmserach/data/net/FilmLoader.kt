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
        val filmApi = RetrofitClient.getClient().create(FilmApi::class.java)
        val field = "genres.name"
        val genres = getAllGenres()

        for (genre in genres) {

            filmApi.getByGenre(TOKEN, field, genre)
                .execute().let {

                    if (it.isSuccessful) {
                        val filmsDto = it.body()?.films
                        val films = FilmConverter.convertListFromDto(filmsDto, genre)

                        filmsByGenresLoaded[genre] = films
                }
            }
        }

        return filmsByGenresLoaded
    }

    fun loadFilmsBySearchOptions(searchOptions: SearchOptions): List<Film> {
        val filmsLoaded = mutableListOf<Film>()
        val options = searchOptions.toString()

        if (options.isEmpty()) {
            return filmsLoaded
        }

        val url = "$PATH/$END_POINT?token=$TOKEN&$options"
        val client = getClient()
        val response = getResponse(client, url)

        if (!response.isSuccessful) {
            return filmsLoaded
        }

        response.body().let {
            val filmsJson = it?.string()
            val filmsDto = Gson().fromJson(filmsJson, FilmsDto::class.java)

            return FilmConverter.convertListFromDto(filmsDto.films)
        }

        return filmsLoaded
    }

    private fun getClient() = OkHttpClient()

    private fun getResponse(client: OkHttpClient, url: String): Response {
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)

        return call.execute()
    }
}