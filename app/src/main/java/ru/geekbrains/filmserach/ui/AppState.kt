package ru.geekbrains.filmserach.ui

import ru.geekbrains.filmserach.ui.base.ResponseData

sealed class AppState {

    data class Success(val data: ResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
