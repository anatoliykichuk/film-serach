package ru.geekbrains.filmserach

import ru.geekbrains.filmserach.model.entities.Film

sealed class AppState {
    data class Success(val film: Film) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
