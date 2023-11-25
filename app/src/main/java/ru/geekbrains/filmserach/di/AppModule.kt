package ru.geekbrains.filmserach.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.data.Repository
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
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java,
            FILM_DATABASE
        )
            .addMigrations(object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE films ADD COLUMN country TEXT")
                }
            })
            .addMigrations(object : Migration(2, 3) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE films RENAME COLUMN country TO countries")
                }
            })
            .addMigrations(object : Migration(3, 4) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE films ADD COLUMN trailers TEXT")
                }
            })
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