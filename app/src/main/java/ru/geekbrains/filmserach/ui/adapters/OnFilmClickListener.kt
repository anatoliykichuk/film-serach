package ru.geekbrains.filmserach.ui.adapters

import ru.geekbrains.filmserach.domain.Film

interface OnFilmClickListener {
    fun onFilmClick(film: Film)
}