package ru.geekbrains.filmserach.data

import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.domain.Film

interface Storable {
    fun isFavorite(filmDatabase: FilmDatabase, film: Film): Boolean
    fun changeFavoritesTag(filmDatabase: FilmDatabase, film: Film): Boolean
    fun getFavorites(filmDatabase: FilmDatabase): List<Film>
    suspend fun getFilmsByGenresFromNet(): Map<String, List<Film>>
    suspend fun getFilmsBySearchOptionsFromNet(searchOptions: SearchOptions): List<Film>
}