package ru.geekbrains.filmserach.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData
import ru.geekbrains.filmserach.ui.pages.settings.Theme

class MainViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    fun loadPreferences() {
        viewModelScope.launch() {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(theme = getSavedTheme())
                    )
                )
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }

    suspend fun getSavedTheme() : Theme {
        try {
            val keyTheme = userPreferences.getSavedTheme().first().toInt()
            return getTheme(keyTheme)
        }   catch (ex: Exception) {
            liveData.postValue(AppState.Error(ex))
            ex.printStackTrace()
        }
        return DEFAULT_THEME
    }

    fun getTheme(keyTheme: Int) : Theme {
        enumValues<Theme>().forEach { theme ->
            if (keyTheme == theme.key) {
                return theme
            }
        }
        return DEFAULT_THEME
    }
}