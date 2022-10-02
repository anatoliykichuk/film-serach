package ru.geekbrains.filmserach.model.example

import ru.geekbrains.filmserach.model.entities.Genres

object CategoryListExample {
    val list: ArrayList<Genres> = arrayListOf()
    private val filmList = FilmListExample.list

    init {
        list.add(Genres("Actions", filmList.toList()))
        list.add(Genres("Fantastic", filmList.toList()))
    }
}