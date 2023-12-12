package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class MainViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    private var dataPosted: Boolean = false

    fun getFilmsByGenres() {
        if (dataPosted) {
            return
        }
        liveData.postValue(AppState.Loading)

        var savedGenres : List<String> = emptyList()
        val preferencesJob = viewModelScope.async(Dispatchers.IO) {
            savedGenres = getSavedGenres()
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                dataPosted = true
                preferencesJob.await().let {
                    liveData.postValue(
                        AppState.Success(
                            ResponseData(filmsByGenres = repository.getFilmsByGenresFromNet(genres = savedGenres))
                        )
                    )
                }
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            } finally {
                dataPosted = false
            }
        }
    }

    suspend fun getSavedGenres() : List<String> {
        try {
            return userPreferences.getSavedGenres().first().toList()
        } catch (ex: Exception) {
            liveData.postValue(AppState.Error(ex))
            ex.printStackTrace()
        }
        return emptyList<String>()
    }
}