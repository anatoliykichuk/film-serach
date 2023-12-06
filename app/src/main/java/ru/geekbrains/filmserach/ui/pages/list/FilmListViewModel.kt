package ru.geekbrains.filmserach.ui.pages.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class FilmListViewModel(
    private val filmDatabase: FilmDatabase
) : BaseViewModel() {

    private var favoritesPosted: Boolean = false
    private var foundPosted: Boolean = false

    fun getFavorites() {
        if (favoritesPosted) {
            return
        }
        liveData.postValue(AppState.Loading)

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                favoritesPosted = true

                liveData.postValue(
                    AppState.Success(
                        ResponseData(films = repository.getFavorites(filmDatabase))
                    )
                )
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }

    fun getFound(searchOptions: SearchOptions) {
        if (foundPosted) {
            return
        }
        liveData.postValue(AppState.Loading)

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                foundPosted = true

                liveData.postValue(
                    AppState.Success(
                        ResponseData(
                            films = repository.getFilmsBySearchOptionsFromNet(searchOptions)
                        )
                    )
                )

            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }
}