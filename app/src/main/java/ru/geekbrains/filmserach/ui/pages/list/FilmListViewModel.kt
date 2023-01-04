package ru.geekbrains.filmserach.ui.pages.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.SearchOptions
import ru.geekbrains.filmserach.ui.AppState

class FilmListViewModel(
    private val filmDatabase: FilmDatabase
    ) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getFavorites() {
        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(
                AppState.SuccessGettingFavoritesFilms(
                    Repository().getFavorites(filmDatabase)
                )
            )
        }.start()
    }

    fun getFound(searchOptions: SearchOptions) {
        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(
                AppState.SuccessGettingFavoritesFilms(
                    Repository().getFilmsBySearchOptionsFromNet(searchOptions)
                )
            )
        }.start()
    }
}