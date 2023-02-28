package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.ui.AppState

class MainViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private var dataPosted: Boolean = false

    fun getLiveData(): LiveData<AppState> = liveData

    fun getFilmsByGenres() {
        if (dataPosted) {
            return
        }

        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(
                AppState.SuccessGettingFilmsByGenre(
                    Repository().getFilmsByGenresFromNet()
                )
            )
            dataPosted = true
        }.start()
    }
}