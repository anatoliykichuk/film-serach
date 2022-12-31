package ru.geekbrains.filmserach.data.net

import ru.geekbrains.filmserach.data.FilmConverter
import ru.geekbrains.filmserach.data.TOKEN
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
        val filmApi = RetrofitClient.getClient().create(FilmApi::class.java)

        filmApi.getBySearchOptions(
                token = TOKEN,
                nameKey = "name",
                nameValue = searchOptions.name,
                genreKey = "genres.name",
                genreValue = searchOptions.genre,
                countryKey = "premiere.country",
                countryValue = searchOptions.country,
                yearKey = "year",
                yearValue = searchOptions.getYearRange(),
                popularityKey = "rating.kp",
                popularityValue = searchOptions.getPopularityRange()
            ).execute().let {

                if (it.isSuccessful) {
                    val filmsDto = it.body()?.films
                    val films = FilmConverter.convertListFromDto(filmsDto)

                    return FilmConverter.convertListFromDto(filmsDto)
            }

            return filmsLoaded
        }
    }
}