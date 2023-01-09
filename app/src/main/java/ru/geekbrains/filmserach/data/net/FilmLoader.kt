package ru.geekbrains.filmserach.data.net

import ru.geekbrains.filmserach.BuildConfig
import ru.geekbrains.filmserach.data.END_POINT
import ru.geekbrains.filmserach.data.FilmConverter
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.domain.SearchOptions

class FilmLoader {

    fun loadFilmsByGenres(): Map<String, List<Film>> {

        val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
        val filmApi = RetrofitClient.getClient().create(FilmApi::class.java)
        val field = "genres.name"
        val genres = getAllGenres()

        for (genre in genres) {

            filmApi.getByGenre(BuildConfig.TOKEN, field, genre)
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
        val filmApi = RetrofitClient.getClient().create(FilmApi::class.java)
        val url = "$END_POINT?token=${BuildConfig.TOKEN}${searchOptions.toString()}"

        filmApi.getBySearchOptions(url)
            .execute().let {

                if (it.isSuccessful) {
                    val filmsDto = it.body()?.films

                    return FilmConverter.convertListFromDto(filmsDto)
            }

            return filmsLoaded
        }
    }
}