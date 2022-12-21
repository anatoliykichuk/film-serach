package ru.geekbrains.filmserach.data

import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

interface Storable {
    fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean
    fun changeFavoritesTag(filmDatabase: FilmDatabase, film: Film)
    fun getFavorites(filmDatabase: FilmDatabase): List<Film>
    fun getFilmsByGenresFromNet(): Map<String, List<Film>>
    fun getFilmsBySearchOptionsFromNet(): List<Film>
}