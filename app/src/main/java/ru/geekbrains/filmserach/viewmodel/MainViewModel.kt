package ru.geekbrains.filmserach.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.AppState
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.repository.FilmByGenresLoader

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilmsByGenres() {
        FilmByGenresLoader(liveData).load()
    }
}