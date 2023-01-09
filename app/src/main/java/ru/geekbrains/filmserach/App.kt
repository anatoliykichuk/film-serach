package ru.geekbrains.filmserach

import android.app.Application
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.geekbrains.filmserach.data.FILM_DATABASE
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        private var filmDatabase: FilmDatabase? = null

        fun getFilmDatabase(context: Context): FilmDatabase {
            if (filmDatabase != null) {
                return filmDatabase!!
            }
            filmDatabase = Room.databaseBuilder(
                context,
                FilmDatabase::class.java,
                FILM_DATABASE
            )
                .allowMainThreadQueries()
                .build()

            return filmDatabase!!
        }
    }
}