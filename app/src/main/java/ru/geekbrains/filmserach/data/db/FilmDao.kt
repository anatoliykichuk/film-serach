package ru.geekbrains.filmserach.data.db

import androidx.room.*
import kotlinx.coroutines.Deferred

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getAll(): Deferred<List<FilmEntity>>

    @Query(
        "SELECT * " +
                "FROM films " +
                "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate"
    )
    suspend fun getByKey(
        title: String, originalTitle: String, releaseDate: String
    ): Deferred<FilmEntity>

    @Query("SELECT * FROM films WHERE isFavorite")
    suspend fun getFavorites(): Deferred<List<FilmEntity>>

    @Query(
        "SELECT isFavorite " +
                "FROM films " +
                "WHERE title = :title " +
                "AND originalTitle = :originalTitle " +
                "AND releaseDate = :releaseDate"
    )
    suspend fun isFavorite(
        title: String, originalTitle: String, releaseDate: String
    ): Deferred<Boolean>

    @Insert
    suspend fun insert(film: FilmEntity)

    @Update
    suspend fun update(film: FilmEntity)

    @Delete
    suspend fun delete(film: FilmEntity)
}
