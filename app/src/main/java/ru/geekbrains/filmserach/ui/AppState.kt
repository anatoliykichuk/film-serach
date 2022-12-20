package ru.geekbrains.filmserach.ui

import ru.geekbrains.filmserach.domain.Film

sealed class AppState {
    data class SuccessGettingFilmsByGenre(val filmsByGenres: Map<String, List<Film>>) : AppState()
    data class SuccessGettingFavoritesFilms(val films: List<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
