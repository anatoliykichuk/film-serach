package ru.geekbrains.filmserach.ui

import ru.geekbrains.filmserach.domain.Film

interface OnFilmClickListener {
    fun onFilmClick(film: Film)
}