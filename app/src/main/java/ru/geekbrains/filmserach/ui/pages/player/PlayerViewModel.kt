package ru.geekbrains.filmserach.ui.pages.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.ui.AppState

class PlayerViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private var urlReceived: Boolean = false

    fun getLiveData(): LiveData<AppState> = liveData

    fun getVideoUrl() {
        if (urlReceived) {
            return
        }

        liveData.value = AppState.Loading
    }
}