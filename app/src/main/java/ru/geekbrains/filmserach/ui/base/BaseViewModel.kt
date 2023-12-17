package ru.geekbrains.filmserach.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmserach.ui.AppState

abstract class BaseViewModel() : ViewModel() {

    protected val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveData
}