package ru.geekbrains.filmserach.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.repository.FilmDao

@Database(entities = [Film::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}