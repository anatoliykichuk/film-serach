package ru.geekbrains.filmserach.model.example

import ru.geekbrains.filmserach.model.entities.Category

object CategoryListExample {
    val list: ArrayList<Category> = arrayListOf()

    init {
        list.add(
            Category("Actions", FilmListExample.list)
        )

        list.add(
            Category("Fantastic", FilmListExample.list)
        )
    }
}