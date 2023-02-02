package ru.geekbrains.filmserach.data

import com.example.example.FilmDto
import ru.geekbrains.filmserach.data.db.FilmEntity
import ru.geekbrains.filmserach.domain.Film
import java.util.stream.Collectors

object FilmConverter {

    fun convertFromDto(filmDto: FilmDto, genre: String = ""): Film {
        return Film(
            title = getTitle(filmDto),
            originalTitle = originalTitle(filmDto),
            genres = genres(filmDto),
            countries = countries(filmDto),
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

    fun convertListFromDto(filmsDto: List<FilmDto>?, genre: String = ""): List<Film> {
        val films = mutableListOf<Film>()

        if (filmsDto == null) {
            return films
        }

        for (filmDto in filmsDto) {
            val film = convertFromDto(filmDto, genre)
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
            countries = filmEntity.countries,
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

    fun convertToEntity(film: Film): FilmEntity {
        return FilmEntity(
            title = film.title,
            originalTitle = film.originalTitle,
            originalLanguage = film.originalLanguage,
            genres = film.genres,
            countries = film.countries,
            releaseDate = film.releaseDate,
            adult = film.adult,
            overview = film.overview,
            video = film.video,
            popularity = film.popularity,
            voteCount = film.voteCount,
            voteAverage = film.voteAverage,
            posterPath = film.posterPath,
            backdropPath = film.backdropPath,
            isFavorite = film.isFavorite
        )
    }

    private fun getTitle(filmDto: FilmDto): String {
        return filmDto.name ?: ""
    }

    private fun originalTitle(filmDto: FilmDto): String {
        return filmDto.alternativeName ?: filmDto.enName ?: ""
    }

    private fun genres(filmDto: FilmDto): List<String> {
        val genres = mutableListOf<String>()

        for (genre in filmDto.genres) {
            genre.name?.let { genres.add(it) }
        }

        return genres
    }

    private fun countries(filmDto: FilmDto): List<String> {
        val countries = mutableListOf<String>()

        for (country in filmDto.countries) {
            country.name?.let { countries.add(it) }
        }

        return countries
    }

    private fun releaseDate(filmDto: FilmDto): String {
        return filmDto.year.toString()
    }

    private fun overview(filmDto: FilmDto): String {
        return filmDto.shortDescription ?: ""
    }

    private fun popularity(filmDto: FilmDto): Double {
        return filmDto.rating?.imdb ?: 0.0
    }

    private fun voteCount(filmDto: FilmDto): Int {
        return filmDto.votes?.imdb ?: 0
    }

    private fun voteAverage(filmDto: FilmDto): Int {
        return filmDto.votes?.filmCritics ?: 0
    }

    private fun posterPath(filmDto: FilmDto): String {
        return filmDto.poster?.url ?: ""
    }
}