package ru.geekbrains.filmserach.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.data.Storable
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class MainFragmentViewModel(
    private val repository: Storable, private val userPreferences: UserPreferences
) : BaseViewModel() {

    private var dataPosted: Boolean = false

    lateinit var searchGenres: List<String>

    private lateinit var savedGenres: List<String>

    fun getFilmsByGenres() {
        if (dataPosted) {
            return
        }
        liveData.postValue(AppState.Loading)

        val preferencesJob = viewModelScope.async(Dispatchers.IO) {
            savedGenres = getSavedGenres()
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                dataPosted = true
                preferencesJob.await().let {
                    searchGenres = savedGenres.filter { genre ->
                        getAllGenres().contains(genre)
                    }
                    searchGenres = searchGenres.ifEmpty { getAllGenres() }
                    liveData.postValue(
                        AppState.Success(
                            ResponseData(
                                filmsByGenres
                                = repository.getFilmsByGenresFromNet(genres = searchGenres)
                            )
                        )
                    )
                }
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }

    suspend fun getSavedGenres(): List<String> {
        try {
            return userPreferences.getSavedGenres().first().toList()
        } catch (ex: Exception) {
            liveData.postValue(AppState.Error(ex))
            ex.printStackTrace()
        }
        return emptyList<String>()
    }
}