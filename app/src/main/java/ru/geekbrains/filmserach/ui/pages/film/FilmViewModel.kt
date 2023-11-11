package ru.geekbrains.filmserach.ui.pages.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.domain.Film

class FilmViewModel(
    private val filmDatabase: FilmDatabase
) : ViewModel() {

    private val liveData: MutableLiveData<Boolean> = MutableLiveData()
    private val repository: Repository by inject(Repository::class.java)
    private var tagPosted: Boolean = false
    private var tagChangePosted: Boolean = false

    fun getLiveData(): LiveData<Boolean> = liveData

    fun isFavorite(film: Film) {
        if (tagPosted) {
            return
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                liveData.value = repository.isFavorite(filmDatabase, film)
                tagPosted = true
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun changeFavoritesTag(film: Film) {
        if (tagChangePosted) {
            return
        }

        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            try {
                liveData.value = repository.changeFavoritesTag(filmDatabase, film)
                tagChangePosted = true
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}