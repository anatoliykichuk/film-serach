package ru.geekbrains.filmserach.data.db

import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getAll(): List<FilmEntity>

    @Query("SELECT * " +
            "FROM films " +
            "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate")
    fun getByKey(title: String, originalTitle: String, releaseDate: String): FilmEntity

    @Query("SELECT * FROM films WHERE isFavorite")
    fun getFavorites(): List<FilmEntity>

    @Query("SELECT isFavorite " +
            "FROM films " +
            "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate")
    fun isFavorite(title: String, originalTitle: String, releaseDate: String): Boolean

    @Insert
    fun insert(film: FilmEntity)

    @Update
    fun update(film: FilmEntity)

    @Delete
    fun delete(film: FilmEntity)
}
