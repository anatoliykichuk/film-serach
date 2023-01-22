package ru.geekbrains.filmserach.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.data.FILM_DATABASE
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.ui.main.MainViewModel
import ru.geekbrains.filmserach.ui.pages.film.FilmViewModel
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel

val appModule = module {

    single<FilmDatabase> {
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java,
            FILM_DATABASE
        ).build()
    }

    viewModel {
        MainViewModel()
    }

    viewModel<FilmViewModel> {
        FilmViewModel(filmDatabase = get())
    }

    viewModel<FilmListViewModel> {
        FilmListViewModel(filmDatabase = get())
    }
}