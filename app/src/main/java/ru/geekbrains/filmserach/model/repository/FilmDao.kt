package ru.geekbrains.filmserach.model.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.geekbrains.filmserach.model.entities.Film

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getAll(): List<Film>

    @Query("SELECT * FROM films WHERE id = :id")
    fun getById(id: Long): Film

    @Insert
    fun insert(film: Film)

    @Update
    fun update(film: Film)

    @Delete
    fun delete(film: Film)
}
