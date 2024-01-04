package ru.geekbrains.filmserach.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData
import ru.geekbrains.filmserach.ui.pages.settings.Theme

class MainViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    private var savedTheme: Theme? = null

    fun getSavedTheme(): Theme {
        return savedTheme ?: Theme.KINOPOISK_THEME
    }

    fun loadSavedTheme() {
        viewModelScope.launch() {
            savedTheme = userPreferences.getSavedTheme()

            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(theme = savedTheme)
                    )
                )
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }
}