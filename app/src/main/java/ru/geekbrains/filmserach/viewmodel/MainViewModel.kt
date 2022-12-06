package ru.geekbrains.filmserach.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.repository.FilmByGenresLoader

class MainViewModel(
    private val liveData: MutableLiveData<Map<String, List<Film>>> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilmsByGenres() {
        FilmByGenresLoader(liveData).load()
    }
}