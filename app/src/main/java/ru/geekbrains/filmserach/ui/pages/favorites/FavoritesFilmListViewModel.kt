package ru.geekbrains.filmserach.ui.pages.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

class FavoritesFilmListViewModel(
    private val filmDatabase: FilmDatabase
    ) : ViewModel() {

    private val liveData: MutableLiveData<List<Film>> = MutableLiveData()

    fun getLiveData(): LiveData<List<Film>> = liveData

    fun getFavorites() {
        Thread {
            liveData.postValue(
                Repository().getFavorites(filmDatabase)
            )
        }.start()
    }
}