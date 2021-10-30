package ru.geekbrains.filmserach.model.entities

data class Film(
    val title: String,
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val genreIds: List<Int>,
    val releaseDate: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val video: Boolean = false,
    val popularity: Float = 0.0F,
    val voteCount: Int = 0,
    val voteAverage: Float = 0.0F,
    val poster: Int = 0,
    val posterPath: String? = "",
    val backdropPath: String? = "") {
}
