package ru.geekbrains.filmserach.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.data.Repository
import ru.geekbrains.filmserach.data.Storable
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_1_2
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_2_3
import ru.geekbrains.filmserach.data.db.DatabaseMigration.MIGRATION_3_4
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.data.net.FilmApi
import ru.geekbrains.filmserach.data.net.FilmLoader
import ru.geekbrains.filmserach.data.net.RetrofitClient
import ru.geekbrains.filmserach.ui.UserPreferences
import ru.geekbrains.filmserach.ui.main.MainViewModel
import ru.geekbrains.filmserach.ui.pages.film.FilmViewModel
import ru.geekbrains.filmserach.ui.pages.list.FilmListViewModel
import ru.geekbrains.filmserach.ui.pages.settings.SettingsViewModel

const val FILM_DATABASE = "film_database"

val appModule = module {

    factory<Storable> {
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
        MainViewModel(repository = get(), userPreferences = get())
    }

    viewModel<FilmViewModel> {
        FilmViewModel(repository = get(), filmDatabase = get())
    }

    viewModel<FilmListViewModel> {
        FilmListViewModel(repository = get(), filmDatabase = get())
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(userPreferences = get())
    }

    single<UserPreferences> {
        UserPreferences(context = androidApplication())
    }
}