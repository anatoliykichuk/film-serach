package ru.geekbrains.filmserach.data

import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.FilmLoader
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.domain.Film

class Repository() : Storable {

    private val filmLoader: FilmLoader by inject(FilmLoader::class.java)

    override suspend fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean {

        val favoriteTags = filmDatabase.getFilmDao().favoriteTags(film.title,
            film.originalTitle, film.releaseDate)

        return favoriteTags.isNotEmpty() && favoriteTags.get(0)
    }

    override suspend fun changeFavoritesTag(filmDatabase: FilmDatabase, film: Film): Boolean {
        film.isFavorite = !film.isFavorite
        val filmEntity = FilmConverter.convertToEntity(film)

        if (film.isFavorite) {
            filmDatabase.getFilmDao().insert(filmEntity)
        } else {
            filmDatabase.getFilmDao().delete(filmEntity)
        }
        return film.isFavorite
    }

    override suspend fun getFavorites(filmDatabase: FilmDatabase): List<Film> {
        filmDatabase.getFilmDao()
            .getFavorites().let {
                return FilmConverter.convertListFromEntity(it)
            }
    }

    override suspend fun getFilmsByGenresFromNet(genres: List<String>): Map<String, List<Film>> {
        return filmLoader.loadFilmsByGenres(genres)
    }

    override suspend fun getFilmsBySearchOptionsFromNet(searchOptions: SearchOptions): List<Film> {
        return filmLoader.loadFilmsBySearchOptions(searchOptions)
    }
}