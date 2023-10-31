package ru.geekbrains.filmserach.ui.pages.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.ui.AppState

class FilmListViewModel(
    private val filmDatabase: FilmDatabase
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val repository: Repository by inject(Repository::class.java)
    private var favoritesPosted: Boolean = false
    private var foundPosted: Boolean = false

    fun getLiveData(): LiveData<AppState> = liveData

    fun getFavorites() {
        if (favoritesPosted) {
            return
        }

        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(
                AppState.SuccessGettingFavoritesFilms(
                    repository.getFavorites(filmDatabase)
                )
            )
            favoritesPosted = true
        }.start()
    }

    fun getFound(searchOptions: SearchOptions) {
        if (foundPosted) {
            return
        }

        liveData.value = AppState.Loading

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            liveData.value = AppState.SuccessGettingFavoritesFilms(
                repository.getFilmsBySearchOptionsFromNet(searchOptions)
            )
            foundPosted = true
        }
    }
}