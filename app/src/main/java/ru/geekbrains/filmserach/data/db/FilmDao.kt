package ru.geekbrains.filmserach.data.db

import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getAll(): List<FilmEntity>

    @Query("SELECT * " +
            "FROM films " +
            "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate")
    suspend fun getByKey(title: String, originalTitle: String, releaseDate: String): FilmEntity

    @Query("SELECT * FROM films WHERE isFavorite")
    suspend fun getFavorites(): List<FilmEntity>

    @Query("SELECT isFavorite " +
            "FROM films " +
            "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate")
    suspend fun isFavorite(title: String, originalTitle: String, releaseDate: String): Boolean

    @Insert
    suspend fun insert(film: FilmEntity)

    @Update
    suspend fun update(film: FilmEntity)

    @Delete
    suspend fun delete(film: FilmEntity)
}
