package ru.geekbrains.filmserach.model.repository

import android.util.Log
import com.example.example.FilmDto
import com.example.example.FilmsDto
import ru.geekbrains.filmserach.model.entities.Film

object FilmConverter {

    fun convert(filmDto: FilmDto): Film {
        return Film(
            title = title(filmDto),
            originalTitle = originalTitle(filmDto),
            originalLanguage = originalLanguage(filmDto),
            genreIds = listOf<Int>(),
            releaseDate = releaseDate(filmDto),
            adult = false,
            overview = overview(filmDto),
            video = false,
            popularity = popularity(filmDto),
            voteCount = voteCount(filmDto),
            voteAverage = voteAverage(filmDto),
            poster = 0, //filmDto.poster!!.url!!,
            posterPath = posterPath(filmDto),
            backdropPath = ""
        )
    }

    fun convertList(filmsDto: List<FilmDto>): List<Film> {
        val films = mutableListOf<Film>()

        for (filmDto in filmsDto) {
            try {
                val film = convert(filmDto)
                films.add(film)

            } catch (e: Error) {
                Log.e("FILM_CONVERTER", e.message.toString())
            }
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