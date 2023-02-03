package ru.geekbrains.filmserach.data.db

import androidx.room.Entity
import androidx.room.TypeConverters

@Entity(tableName = "films", primaryKeys = ["title", "originalTitle", "releaseDate"])
data class FilmEntity(
    val title: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    @TypeConverters(ListConverter::class) val genres: List<String> = listOf<String>(),
    @TypeConverters(ListConverter::class) val countries: List<String> = listOf<String>(),
    val releaseDate: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val video: Boolean = false,
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Int = 0,
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val isFavorite: Boolean = false
) {

}
