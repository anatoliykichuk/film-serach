package ru.geekbrains.filmserach.model.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    @TypeConverters(GenresConverter::class) val genres: List<String> = listOf<String>(),
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
