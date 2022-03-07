package ru.geekbrains.filmserach.model.example

import ru.geekbrains.filmserach.model.entities.Category
import ru.geekbrains.filmserach.model.entities.Film

object CategoryListExample {
    val list: ArrayList<Category> = arrayListOf()
    private val filmList = FilmListExample.list

    init {
        list.add(Category("Actions", filmList.toList()))
        list.add(Category("Fantastic", filmList.toList()))
    }
}