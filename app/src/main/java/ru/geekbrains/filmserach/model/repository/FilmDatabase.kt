package ru.geekbrains.filmserach.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.GenresConverter
import ru.geekbrains.filmserach.model.entities.PosterConverter

@TypeConverters(GenresConverter::class, PosterConverter::class)
@Database(entities = [Film::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}