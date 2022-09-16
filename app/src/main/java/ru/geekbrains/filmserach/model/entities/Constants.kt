package ru.geekbrains.filmserach.model.entities

const val PATH = "https://api.kinopoisk.dev"
const val END_POINT = "movie"
const val TOKEN = "AF2F5V2-SGRM6NN-JDNY2ZC-K6Z71K7"

fun getAllGenres(): Map<String, String> {
    return mapOf(
            "boevik" to "боевик",
            "fentezi" to "фэнтези",
            "fantastika" to "фантастика",
            "triller" to "триллер",
            "voennyj" to "военный",
            "detektiv" to "детектив",
            "komediya" to "комедия",
            "drama" to "драма",
            "uzhasy" to "ужасы",
            "kriminal" to "криминал",
            "melodrama" to "мелодрама",
            "vestern" to "вестерн",
            "biografiya" to "биография",
            "anime" to "аниме",
            "detskij" to "детский",
            "multfilm" to "мультфильм",
            "film-nuar" to "фильм-нуар",
            "dlya-vzroslyh" to "для взрослых",
            "dokumentalnyj" to "документальный",
            "igra" to "игра",
            "istoriya" to "история",
            "koncert" to "концерт",
            "korotkometrazhka" to "короткометражка",
            "muzyka" to "музыка",
            "myuzikl" to "мюзикл",
            "novosti" to "новости",
            "priklyucheniya" to "приключения",
            "realnoe-tv" to "реальное ТВ",
            "semejnyj" to "семейный",
            "sport" to "спорт",
            "tok-shou" to "ток-шоу",
            "ceremoniya" to "церемония"
    )
}
