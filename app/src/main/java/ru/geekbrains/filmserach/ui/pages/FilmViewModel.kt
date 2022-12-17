package ru.geekbrains.filmserach.ui.pages

import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.domain.Film

class FilmViewModel : ViewModel() {

    // Надо в факбикку ViewModel передавать базу данных из фрагмента/активити

    fun changFavoritesTag(film: Film?) {
        if (film!!.isFavorite) {

        } else {

        }
    }
}