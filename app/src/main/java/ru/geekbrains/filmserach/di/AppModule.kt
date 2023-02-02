package ru.geekbrains.filmserach.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
        )
//            .addMigrations(object : Migration(1, 2) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL("ALTER TABLE films ADD COLUMN country TEXT")
//                }
//            })
//            .addMigrations(object : Migration(2, 3) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL("ALTER TABLE films RENAME COLUMN country TO countries")
//                }
//            })
            .fallbackToDestructiveMigration()
            .build()
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