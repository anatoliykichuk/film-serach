package ru.geekbrains.filmserach.ui.pages.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

class FilmViewModel(
    private val filmDatabase: FilmDatabase
    ) : ViewModel() {

    private val liveData: MutableLiveData<Film> = MutableLiveData()

    fun getLiveData(): LiveData<Film> = liveData

    fun isFavorite(film: Film): Boolean = Repository().isFavorite(filmDatabase, film)

    fun changeFavoritesTag(film: Film) = Repository().changeFavoritesTag(filmDatabase, film)
}