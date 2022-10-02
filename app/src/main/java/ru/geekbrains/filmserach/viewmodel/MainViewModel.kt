package ru.geekbrains.filmserach.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.FilmDto
import ru.geekbrains.filmserach.model.repository.FilmByGenresLoader

class MainViewModel(
    private val liveData: MutableLiveData<Map<String, List<FilmDto>>> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilmsByGenre() {
        FilmByGenresLoader(liveData).load()
    }
}