package ru.geekbrains.filmserach.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.ui.AppState

abstract class BaseViewModel : ViewModel() {

    protected val repository: Repository by inject(Repository::class.java)

    protected val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveData
}