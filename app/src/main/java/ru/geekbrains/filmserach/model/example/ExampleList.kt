package ru.geekbrains.filmserach.model.example

import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.Film

object ExampleList {
    var list: ArrayList<Film> = arrayListOf()
        get() {
            list.add(
                Film("Не время умирать", "No Time to Die", "en",
                    listOf(12),"2021-01-01", false, "", false,
                    7.3F, 23000, 0.0F, R.drawable.poster01.toString())
            )

            list.add(
                Film("Семейка Аддамс", "The Addams Family", "en",
                    listOf(12),"2019-01-01", false, "", false,
                    6.5F, 96000, 0.0F, R.drawable.poster02.toString())
            )

            list.add(
                Film("Босс-молокосос 2", "The Boss Baby: Family Business", "en",
                    listOf(6),"2021-01-01", false, "", false,
                    6.0F, 31000, 0.0F, R.drawable.poster03.toString())
            )

            list.add(
                Film("Суперзвезда", "France", "en",
                    listOf(16),"2021-01-01", false, "", false,
                    5.7F, 282, 0.0F, R.drawable.poster04.toString())
            )

            list.add(
                Film("Эйфель", "Eiffel", "en",
                    listOf(16),"2021-01-01", false, "", false,
                    6.6F, 344, 0.0F, R.drawable.poster05.toString())
            )

            list.add(
                Film("Гладиатор", "Gladiator", "en",
                    listOf(16),"2000-01-01", false, "", false,
                    8.6F, 458000, 0.0F, R.drawable.poster06.toString())
            )



            return list
        }
}