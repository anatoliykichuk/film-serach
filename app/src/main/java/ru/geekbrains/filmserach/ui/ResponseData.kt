package ru.geekbrains.filmserach.ui

import ru.geekbrains.filmserach.domain.Film

data class ResponseData(
    val filmsByGenres: Map<String, List<Film>>? = null,
    val films: List<Film>? = null,
    val isFavorite: Boolean? = null
)
