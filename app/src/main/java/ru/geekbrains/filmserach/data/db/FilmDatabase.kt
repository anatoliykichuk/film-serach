package ru.geekbrains.filmserach.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(GenresConverter::class, CountriesConverter::class)
@Database(entities = [FilmEntity::class], version = 3)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}