package ru.geekbrains.filmserach.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Docs
import ru.geekbrains.filmserach.model.entities.getAllGenres
import ru.geekbrains.filmserach.model.repository.FilmLoader

class MainViewModel(
    private val liveData: MutableLiveData<Map<String, List<Docs>>> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilms() {
        FilmLoader("genres.name", getAllGenres(), liveData).load()
    }
}