package ru.geekbrains.filmserach.ui.pages.film

import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

class FilmViewModel(
    private val filmDatabase: FilmDatabase
    ) : ViewModel() {

    fun isFavorite(film: Film): Boolean = Repository().isFavorite(filmDatabase, film)

    fun changeFavoritesTag(film: Film) = Repository().changeFavoritesTag(filmDatabase, film)
}