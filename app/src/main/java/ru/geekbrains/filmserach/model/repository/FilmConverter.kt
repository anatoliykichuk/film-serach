package ru.geekbrains.filmserach.model.repository

import android.graphics.Bitmap
import com.example.example.FilmDto
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.FilmEntity

object FilmConverter {

    fun convertFromDto(filmDto: FilmDto): Film {
        return Film(
            title = title(filmDto),
            originalTitle = originalTitle(filmDto),
            originalLanguage = originalLanguage(filmDto),
            genres = listOf<String>(),
            releaseDate = releaseDate(filmDto),
            adult = false,
            overview = overview(filmDto),
            video = false,
            popularity = popularity(filmDto),
            voteCount = voteCount(filmDto),
            voteAverage = voteAverage(filmDto),
            posterPath = posterPath(filmDto),
            backdropPath = ""
        )
    }

    fun convertListFromDto(filmsDto: List<FilmDto>): List<Film> {
        val films = mutableListOf<Film>()

        for (filmDto in filmsDto) {
            val film = convertFromDto(filmDto)
            films.add(film)
        }
        return films
    }

    fun convertFromEntity(filmEntity: FilmEntity): Film {
        return Film(
            title = filmEntity.title,
            originalTitle = filmEntity.originalTitle,
            originalLanguage = filmEntity.originalLanguage,
            genres = filmEntity.genres,
            releaseDate = filmEntity.releaseDate,
            adult = filmEntity.adult,
            overview = filmEntity.overview,
            video = filmEntity.video,
            popularity = filmEntity.popularity,
            voteCount = filmEntity.voteCount,
            voteAverage = filmEntity.voteAverage,
            posterPath = filmEntity.posterPath,
            backdropPath = filmEntity.backdropPath,
            isFavorite = filmEntity.isFavorite
        )
    }

    fun convertListFromEntity(filmsEntity: List<FilmEntity>): List<Film> {
        val films = mutableListOf<Film>()

        for (filmEntity in filmsEntity) {
            val film = convertFromEntity(filmEntity)
            films.add(film)
        }
        return films
    }

    private fun title(filmDto: FilmDto): String {
        return filmDto?.name ?: ""
    }

    private fun originalTitle(filmDto: FilmDto): String {
        return filmDto?.alternativeName ?: ""
    }

    private fun originalLanguage(filmDto: FilmDto): String {
        return filmDto?.movieLength ?: ""
    }

    private fun releaseDate(filmDto: FilmDto): String {
        return filmDto?.year.toString() ?: ""
    }

    private fun overview(filmDto: FilmDto): String {
        return filmDto?.shortDescription ?: ""
    }

    private fun popularity(filmDto: FilmDto): Double {
        return filmDto?.rating?.imdb ?: 0.0
    }

    private fun voteCount(filmDto: FilmDto): Int {
        return filmDto?.votes?.imdb ?: 0
    }

    private fun voteAverage(filmDto: FilmDto): Int {
        return filmDto?.votes?.filmCritics ?: 0
    }

    private fun posterPath(filmDto: FilmDto): String {
        return filmDto?.poster?.url ?: ""
    }
}