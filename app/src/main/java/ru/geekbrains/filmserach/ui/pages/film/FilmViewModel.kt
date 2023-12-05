package ru.geekbrains.filmserach.ui.pages.film

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.BaseViewModel
import ru.geekbrains.filmserach.ui.ResponseData

class FilmViewModel(
    private val filmDatabase: FilmDatabase
) : BaseViewModel() {

    private var tagPosted: Boolean = false
    private var tagChangePosted: Boolean = false

    fun isFavorite(film: Film) {
        if (tagPosted) {
            return
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                tagPosted = true
                liveData.postValue(AppState.Success(
                    ResponseData(isFavorite = repository.isFavorite(filmDatabase, film))))
            }
            catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
            finally {
                tagPosted = false
            }
        }
    }

    fun changeFavoritesTag(film: Film) {
        if (tagChangePosted) {
            return
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                tagChangePosted = true
                liveData.postValue(AppState.Success(ResponseData(isFavorite = repository.changeFavoritesTag(filmDatabase, film))))
            }
            catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
            finally {
                tagChangePosted = false
            }
        }
    }
}