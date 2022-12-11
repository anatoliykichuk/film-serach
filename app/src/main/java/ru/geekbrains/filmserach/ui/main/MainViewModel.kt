package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.AppState
import ru.geekbrains.filmserach.data.net.FilmByGenresLoader

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilmsByGenres() {
        FilmByGenresLoader(liveData).load()
    }
}