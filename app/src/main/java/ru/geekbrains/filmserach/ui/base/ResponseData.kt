package ru.geekbrains.filmserach.ui.base

import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.pages.settings.Theme

data class ResponseData(
    val filmsByGenres: Map<String, List<Film>>? = null,
    val films: List<Film>? = null,
    val genres: List<String>? = null,
    val theme: Theme? = null,
    val isFavorite: Boolean? = null
)
