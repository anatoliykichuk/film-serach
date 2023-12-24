package ru.geekbrains.filmserach.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.geekbrains.filmserach.ui.base.BaseViewModel
import ru.geekbrains.filmserach.ui.base.ResponseData

class MainViewModel(val userPreferences: UserPreferences) : BaseViewModel() {

    fun loadPreferences() {
        viewModelScope.launch() {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(theme = userPreferences.getSavedTheme())
                    )
                )
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(e))
                e.printStackTrace()
            }
        }
    }
}