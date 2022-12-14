package ru.geekbrains.filmserach.data

import ru.geekbrains.filmserach.domain.Film

interface Storable {
    fun getFilmsByGenresFromDb() : Map<String, List<Film>>
    fun getFilmsByGenresFromNet() : Map<String, List<Film>>
}