package ru.geekbrains.filmserach.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchOptions(
    val name: String,
    val genre: String,
    val country: String,
    val startYear: Float,
    val endYear: Float,
    val startPopularity: Float,
    val endPopularity: Float
) : Parcelable {
}