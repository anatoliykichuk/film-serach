package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.ui.AppState

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getFilmsByGenres() {
        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(
                AppState.Success(
                    Repository().getFilmsByGenresFromNet()
                )
            )
        }.start()
    }
}