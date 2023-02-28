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
    private var tagPosted: Boolean = false
    private var tagChangePosted: Boolean = false

    fun getLiveData(): LiveData<Boolean> = liveData

    fun isFavorite(film: Film) {
        if (tagPosted) {
            return
        }

        Thread {
            liveData.postValue(
                Repository().isFavorite(filmDatabase, film)
            )
            tagPosted = true
        }.start()
    }

    fun changeFavoritesTag(film: Film) {
        if (tagChangePosted) {
            return
        }

        Thread {
            liveData.postValue(
                Repository().changeFavoritesTag(filmDatabase, film)
            )
            tagChangePosted = true
        }.start()
    }

}