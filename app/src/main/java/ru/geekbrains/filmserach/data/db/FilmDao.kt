package ru.geekbrains.filmserach.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.geekbrains.filmserach.domain.Film

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getAll(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE id = :id")
    fun getById(id: Long): FilmEntity

    @Insert
    fun insert(film: FilmEntity)

    @Update
    fun update(film: FilmEntity)

    @Delete
    fun delete(film: FilmEntity)
}
