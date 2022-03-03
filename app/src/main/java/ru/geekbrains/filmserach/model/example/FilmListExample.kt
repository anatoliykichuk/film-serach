package ru.geekbrains.filmserach.model.example

import org.koin.core.component.getScopeId
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.Film

object FilmListExample {
    var list: ArrayList<Film> = arrayListOf()

    init {
        list.add(
            Film("Не время умирать", "No Time to Die", "en",
                listOf(12),"2021-01-01", false, "", false,
                7.3F, 23000, 0.0F, R.drawable.poster01.toInt())
        )

        list.add(
            Film("Семейка Аддамс", "The Addams Family", "en",
                listOf(12),"2019-01-01", false, "", false,
                6.5F, 96000, 0.0F, R.drawable.poster02.toInt())
        )

        list.add(
            Film("Босс-молокосос 2", "The Boss Baby: Family Business", "en",
                listOf(6),"2021-01-01", false, "", false,
                6.0F, 31000, 0.0F, R.drawable.poster03.toInt())
        )

        list.add(
            Film("Суперзвезда", "France", "en",
                listOf(16),"2021-01-01", false, "", false,
                5.7F, 282, 0.0F, R.drawable.poster04.toInt())
        )

        list.add(
            Film("Эйфель", "Eiffel", "en",
                listOf(16),"2021-01-01", false, "", false,
                6.6F, 344, 0.0F, R.drawable.poster05.toInt())
        )

        list.add(
            Film("Гладиатор", "Gladiator", "en",
                listOf(16),"2000-01-01", false, "", false,
                8.6F, 458000, 0.0F, R.drawable.poster06.toInt())
        )

        list.add(
            Film("Главный герой", "Free Guy", "en",
                listOf(16),"2021-01-01", false, "", false,
                7.3F, 728000, 0.0F, R.drawable.poster07.toInt())
        )

        list.add(
            Film("Друзья: Воссоединение", "Friends: The Reunion", "en",
                listOf(16),"2021-01-01", false, "", false,
                8.6F, 40000, 0.0F, R.drawable.poster08.toInt())
        )

        list.add(
            Film("Зеленая книга", "Green Book", "en",
                listOf(16),"2018-01-01", false, "", false,
                8.4F, 522000, 0.0F, R.drawable.poster09.toInt())
        )
    }
}