package ru.geekbrains.filmserach.data

import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.FilmLoader
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.domain.Film

class Repository() : Storable {

    private val filmLoader: FilmLoader by inject(FilmLoader::class.java)

    override suspend fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean {
        filmDatabase.getFilmDao()
            .isFavorite(film.title, film.originalTitle, film.releaseDate)
            .await().let {
                return it
            }
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
            .getFavorites()
            .await().let {
                return FilmConverter.convertListFromEntity(it)
            }
    }

    override suspend fun getFilmsByGenresFromNet(): Map<String, List<Film>> {
        return filmLoader.loadFilmsByGenres()
    }

    override suspend fun getFilmsBySearchOptionsFromNet(searchOptions: SearchOptions): List<Film> {
        return filmLoader.loadFilmsBySearchOptions(searchOptions)
    }
}