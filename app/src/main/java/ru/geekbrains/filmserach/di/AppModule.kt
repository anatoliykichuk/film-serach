package ru.geekbrains.filmserach.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_1_2
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_2_3
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_3_4
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.FilmApi
import ru.geekbrains.filmserach.data.net.FilmLoader
import ru.geekbrains.filmserach.data.net.RetrofitClient
import ru.geekbrains.filmserach.ui.main.MainViewModel
import ru.geekbrains.filmserach.ui.pages.film.FilmViewModel
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel

const val FILM_DATABASE = "film_database"

val appModule = module {

    factory<Repository> {
        Repository()
    }

    single<FilmApi> {
        RetrofitClient.getClient().create(FilmApi::class.java)
    }

    factory<FilmLoader> {
        FilmLoader(filmApi = get())
    }

    single<FilmDatabase> {
        Room.databaseBuilder(get(), FilmDatabase::class.java, FILM_DATABASE)
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
            .build()
    }

    viewModel<MainViewModel> {
        MainViewModel()
    }

    viewModel<FilmViewModel> {
        FilmViewModel(filmDatabase = get())
    }

    viewModel<FilmListViewModel> {
        FilmListViewModel(filmDatabase = get())
    }
}