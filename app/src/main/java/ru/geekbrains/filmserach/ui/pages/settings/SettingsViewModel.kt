package ru.geekbrains.filmserach.ui.pages.settings

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class SettingsViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    val allGenres = getAllGenres().toMutableList()

    var checkedGenres : MutableList<String>? = null

    fun loadSavedGenres() {
        val preferencesJob = viewModelScope.async(Dispatchers.IO) {
            try {
                checkedGenres = userPreferences.getSavedGenres().first().toMutableList()
            }
            catch(ex : Exception) {
                liveData.postValue(AppState.Error(ex))
                ex.printStackTrace()
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            preferencesJob.await().let {
                checkedGenres?.let {
                    liveData.postValue(AppState.Success(ResponseData(genres = checkedGenres)))
                }
            }
        }
    }

    fun addGenre(genre: String) {
        checkedGenres?.let {
            if (!it.contains(genre)) {
                it.add(genre)
            }
            saveCheckedGenres()
        }
    }

    fun removeGenre(genre: String) {
        checkedGenres?.let {
            if (it.contains(genre)) {
                it.remove(genre)
            }
            saveCheckedGenres()
        }
    }

    private fun saveCheckedGenres() {
        checkedGenres?.let {
            viewModelScope.launch(Dispatchers.IO) {
                userPreferences.saveGenres(it.toSet())
            }
        }
    }
}
