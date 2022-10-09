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
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Int = 0,
    val poster: Int = 0,
    val posterPath: String? = "",
    val backdropPath: String? = "") {
}
