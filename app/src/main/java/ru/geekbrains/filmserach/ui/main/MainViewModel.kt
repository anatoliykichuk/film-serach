package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.ui.AppState

class MainViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val repository: Repository by inject(Repository::class.java)
    private var dataPosted: Boolean = false

    fun getLiveData(): LiveData<AppState> = liveData

    fun getFilmsByGenres() {
        if (dataPosted) {
            return
        }

        liveData.value = AppState.Loading

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            liveData.value = AppState.SuccessGettingFilmsByGenre(
                repository.getFilmsByGenresFromNet()
            )
            dataPosted = true
        }
    }
}