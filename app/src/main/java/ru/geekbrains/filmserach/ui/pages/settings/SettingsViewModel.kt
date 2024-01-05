package ru.geekbrains.filmserach.ui.pages.settings

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class SettingsViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    private var savedGenres : List<String>? = null

    fun loadSavedGenres() {
        savedGenres?.let {
            liveData.postValue(AppState.Success(ResponseData(genres = savedGenres)))
            return@let
        }
        val preferencesJob = viewModelScope.async(Dispatchers.IO) {
            try {
                savedGenres = userPreferences.getSavedGenres().first().toMutableList()
            }
            catch(ex : Exception) {
                liveData.postValue(AppState.Error(ex))
                ex.printStackTrace()
            }
        }
        viewModelScope.launch {
            preferencesJob.await().let {
                savedGenres?.let {
                    liveData.postValue(AppState.Success(ResponseData(genres = savedGenres)))
                }
            }
        }
    }

    fun saveGenres(checkedGenres: List<String>) {
        checkedGenres?.let {
            viewModelScope.launch(Dispatchers.IO) {
                userPreferences.saveGenres(it.toSet())
                savedGenres = checkedGenres
            }
        }
    }
}
