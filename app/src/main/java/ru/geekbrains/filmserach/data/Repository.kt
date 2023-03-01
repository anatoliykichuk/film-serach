package ru.geekbrains.filmserach.data

import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.db.FilmEntity
import ru.geekbrains.filmserach.data.net.FilmLoader
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.domain.Film

class Repository() : Storable {

    private val filmLoader: FilmLoader by inject(FilmLoader::class.java)

    override fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean {
        return filmDatabase.filmDao()
            .isFavorite(film.title, film.originalTitle, film.releaseDate)
    }

    override fun changeFavoritesTag(filmDatabase: FilmDatabase, film: Film): Boolean {
        film.isFavorite = !film.isFavorite
        val filmEntity = FilmConverter.convertToEntity(film)

        if (film.isFavorite) {
            filmDatabase.filmDao().insert(filmEntity)
        } else {
            filmDatabase.filmDao().delete(filmEntity)
        }
        return film.isFavorite
    }

    override fun getFavorites(filmDatabase: FilmDatabase): List<Film> {
        val filmsEntity: List<FilmEntity> = filmDatabase.filmDao().getFavorites()
        return FilmConverter.convertListFromEntity(filmsEntity)
    }

    override fun getFilmsByGenresFromNet(): Map<String, List<Film>> {
        return filmLoader.loadFilmsByGenres()
    }

    override fun getFilmsBySearchOptionsFromNet(searchOptions: SearchOptions): List<Film> {
        return filmLoader.loadFilmsBySearchOptions(searchOptions)
    }
}