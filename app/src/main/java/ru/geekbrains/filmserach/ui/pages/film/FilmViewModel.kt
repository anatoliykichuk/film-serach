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

    private val liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getLiveData(): LiveData<Boolean> = liveData

    fun isFavorite(film: Film) {
        Thread {
            liveData.postValue(
                Repository().isFavorite(filmDatabase, film)
            )
        }.start()
    }

    fun changeFavoritesTag(film: Film) {
        Thread {
            Repository().changeFavoritesTag(filmDatabase, film)
        }.start()
    }

}