package ru.geekbrains.filmserach.viewmodel

import com.example.example.Docs

sealed class DownloadState {
    data class Done(val filmsByGenres: Map<String, List<Docs>>): DownloadState()
    data class InProgress(val progress: Int): DownloadState()
    data class Error(val error: Throwable): DownloadState()
}
