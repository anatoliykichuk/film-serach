package ru.geekbrains.filmserach.data

import ru.geekbrains.filmserach.R

const val PATH = "https://api.kinopoisk.dev"
const val END_POINT = "movie"
const val TOKEN = "AF2F5V2-SGRM6NN-JDNY2ZC-K6Z71K7"
const val FILM_DATABASE = "film_database"
const val SELECTED_FILM = "selected_film"
const val EMPTY_POSTER_PATH = R.drawable.no_poster

fun getAllGenres(): List<String> {
    return listOf<String>(
            "боевик",
            "фэнтези",
            "фантастика"
//            "триллер",
//            "военный",
//            "детектив",
//            "комедия",
//            "драма",
//            "ужасы",
//            "криминал",
//            "мелодрама",
//            "вестерн",
//            "биография",
//            "аниме",
//            "детский",
//            "мультфильм",
//            "фильм-нуар",
//            "для взрослых",
//            "документальный",
//            "игра",
//            "история",
//            "концерт",
//            "короткометражка",
//            "музыка",
//            "мюзикл",
//            "новости",
//            "приключения",
//            "реальное ТВ",
//            "семейный",
//            "спорт",
//            "ток-шоу",
//            "церемония"
    )
}
