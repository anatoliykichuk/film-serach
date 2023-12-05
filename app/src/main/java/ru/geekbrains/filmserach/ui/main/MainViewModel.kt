package ru.geekbrains.filmserach.ui.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class MainViewModel : BaseViewModel() {

    private var dataPosted: Boolean = false

    fun getFilmsByGenres() {
        if (dataPosted) {
            return
        }
        liveData.postValue(AppState.Loading)

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                dataPosted = true
                liveData.postValue(AppState.Success(
                    ResponseData(filmsByGenres = repository.getFilmsByGenresFromNet())
                )
                )
            }
            catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
            finally {
                dataPosted = false
            }
        }
    }
}