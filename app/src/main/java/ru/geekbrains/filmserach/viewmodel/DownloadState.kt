package ru.geekbrains.filmserach.viewmodel

import com.example.example.FilmDto

sealed class DownloadState {
    data class Done(val filmsByGenres: Map<String, List<FilmDto>>): DownloadState()
    data class InProgress(val progress: Int): DownloadState()
    data class Error(val error: Throwable): DownloadState()
}
