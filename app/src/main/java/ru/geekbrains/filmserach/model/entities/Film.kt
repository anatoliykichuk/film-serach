package ru.geekbrains.filmserach.model.entities

import android.graphics.Bitmap
import android.net.Uri
import java.net.URI

data class Film(
    val title: String,
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val genreIds: List<Int>,
    val releaseDate: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val video: Boolean = false,
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Int = 0,
    val poster: Bitmap? = null,
    val posterPath: String? = "",
    val backdropPath: String? = "") {
}
