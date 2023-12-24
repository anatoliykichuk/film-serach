package ru.geekbrains.filmserach.ui.pages.settings

import androidx.annotation.StringRes
import ru.geekbrains.filmserach.R

enum class Theme(
    val key: Int,
    @StringRes val nameId: Int,
    val buttonId: Int
) {
    KINOPOISK_THEME(R.style.kinoPoiskTheme, R.string.kinopoisk_theme_name, R.id.kinopoisk_theme),
    LIME_THEME(R.style.limeTheme, R.string.lime_theme_name, R.id.lime_theme)
}