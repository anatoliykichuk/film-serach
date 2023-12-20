package ru.geekbrains.filmserach.data.net

import ru.geekbrains.filmserach.BuildConfig
import ru.geekbrains.filmserach.data.FilmConverter
import ru.geekbrains.filmserach.data.getSelectedFields
import ru.geekbrains.filmserach.domain.Film

class FilmLoader(private val filmApi: FilmApi) {

    suspend fun loadFilmsByGenres(genres: List<String>): Map<String, List<Film>> {
        try {
            return loadFilmsByGenresSafety(genres)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return mapOf<String, List<Film>>()
    }

    suspend fun loadFilmsBySearchOptions(searchOptions: SearchOptions): List<Film> {
        try {
            return loadFilmsBySearchOptionsSafety(searchOptions)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return listOf<Film>()
    }

    private suspend fun loadFilmsByGenresSafety(genres: List<String>): Map<String, List<Film>> {
        val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
        val selectedFields = getSelectedFields()

        for (genre in genres) {
            filmApi.getByGenre(
                BuildConfig.TOKEN, genre, selectedFields
            ).await().let {

                val filmsDto = it.films
                val films = FilmConverter.convertListFromDto(filmsDto)

                filmsByGenresLoaded[genre] = films
            }
        }
        return filmsByGenresLoaded
    }

    private suspend fun loadFilmsBySearchOptionsSafety(
        searchOptions: SearchOptions
    ): List<Film> {
        val currentEndPoint = if (searchOptions.name.isNotEmpty()) {
            END_POINT_FOR_SEARCH_BY_NAME
        } else {
            END_POINT
        }
        val url = "${currentEndPoint}?${searchOptions.toString()}"

        filmApi.getBySearchOptions(BuildConfig.TOKEN, url).await().let {
            return FilmConverter.convertListFromDto(it.films)
        }
    }
}