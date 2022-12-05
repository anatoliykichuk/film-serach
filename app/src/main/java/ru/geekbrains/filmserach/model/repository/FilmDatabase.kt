package ru.geekbrains.filmserach.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.GenresConverter
import ru.geekbrains.filmserach.model.repository.FilmDao

@Database(entities = [Film::class], version = 1)
//@TypeConverters(GenresConverter::class)
//@TypeConverters(PosterConverter::class)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}