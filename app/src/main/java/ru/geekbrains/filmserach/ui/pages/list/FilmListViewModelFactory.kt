package ru.geekbrains.filmserach.ui.pages.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.data.db.FilmDatabase

class FilmListViewModelFactory(
    private val context: Context?
    ) : ViewModelProvider.Factory {

    private val appContext
        get() = context!!

    private lateinit var filmDatabase: FilmDatabase

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        filmDatabase = App.getFilmDatabase(appContext)
        return FilmListViewModel(filmDatabase) as T
    }
}