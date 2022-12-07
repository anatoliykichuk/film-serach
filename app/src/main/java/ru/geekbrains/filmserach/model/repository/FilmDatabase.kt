package ru.geekbrains.filmserach.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.FilmEntity
import ru.geekbrains.filmserach.model.entities.GenresConverter
import ru.geekbrains.filmserach.model.entities.PosterConverter
import ru.geekbrains.filmserach.model.repository.FilmDao

@TypeConverters(GenresConverter::class, PosterConverter::class)
@Database(entities = [FilmEntity::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}