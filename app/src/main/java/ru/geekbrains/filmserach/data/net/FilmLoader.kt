package ru.geekbrains.filmserach.data.net

import ru.geekbrains.filmserach.BuildConfig
import ru.geekbrains.filmserach.data.FilmConverter
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.data.getSelectedFields
import ru.geekbrains.filmserach.domain.Film

class FilmLoader(private val filmApi: FilmApi) {

    suspend fun loadFilmsByGenres(): Map<String, List<Film>> {
        try {
            return loadFilmsByGenresSafety()
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

    private suspend fun loadFilmsByGenresSafety(): Map<String, List<Film>> {
        val filmsByGenresLoaded = mutableMapOf<String, List<Film>>()
        val genres = getAllGenres()
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
        val url = "${END_POINT}?${searchOptions.toString()}"

        filmApi.getBySearchOptions(BuildConfig.TOKEN, url).await().let {
            return FilmConverter.convertListFromDto(it.films)
        }
    }
}