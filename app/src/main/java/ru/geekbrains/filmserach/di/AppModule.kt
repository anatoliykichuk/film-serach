package ru.geekbrains.filmserach.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.ui.main.MainViewModel
import ru.geekbrains.filmserach.ui.pages.film.FilmViewModel
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel

val appModule = module {

    single<FilmDatabase> {
        App.getFilmDatabase(context = get())
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