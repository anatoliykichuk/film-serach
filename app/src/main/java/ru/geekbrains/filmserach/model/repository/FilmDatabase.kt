package ru.geekbrains.filmserach.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.geekbrains.filmserach.model.entities.FilmEntity
import ru.geekbrains.filmserach.model.entities.GenresConverter

@TypeConverters(GenresConverter::class)
@Database(entities = [FilmEntity::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}