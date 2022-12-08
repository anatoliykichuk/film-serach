package ru.geekbrains.filmserach.model.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

data class Film(
    val title: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val genres: List<String> = listOf<String>(),
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
