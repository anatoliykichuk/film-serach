package ru.geekbrains.filmserach.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.FilmConverter
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

class FilmViewModel(
    private val filmDatabase: FilmDatabase
    ) : ViewModel() {

    private val liveData: MutableLiveData<Film> = MutableLiveData()

    fun getLiveData(): LiveData<Film> = liveData

    fun isFavorite(film: Film): Boolean {
        return filmDatabase.filmDao()
            .isFavorite(film.title, film.originalTitle, film.releaseDate)
    }

    fun changeFavoritesTag(film: Film) {
        film.isFavorite = !film.isFavorite
        val filmEntity = FilmConverter.convertToEntity(film)

        if (film.isFavorite) {
            filmDatabase.filmDao().insert(filmEntity)
        } else {
            filmDatabase.filmDao().delete(filmEntity)
        }
    }
}