package ru.geekbrains.filmserach.data

import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.db.FilmEntity
import ru.geekbrains.filmserach.data.net.dto.FilmLoader
import ru.geekbrains.filmserach.domain.*

class Repository() : Storable {
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
        return FilmLoader().loadFilmsByGenres()
    }
}