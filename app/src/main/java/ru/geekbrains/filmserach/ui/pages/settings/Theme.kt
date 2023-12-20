package ru.geekbrains.filmserach.ui.pages.settings

import androidx.annotation.StringRes
import ru.geekbrains.filmserach.R

enum class Theme (val key: Int,
    @StringRes val nameTheme: Int,
    val theme: Int
) {
    KINOPOISK_THEME(1, R.string.kinoPoisk_theme_name, R.style.kinoPoiskTheme),
    LIME_THEME(2, R.string.lime_theme_name, R.style.limeTheme)
}