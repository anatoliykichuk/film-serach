package ru.geekbrains.filmserach.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.geekbrains.filmserach.data.*
import java.time.LocalDate

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

    override fun toString(): String {
        val optionsBuilder: MutableList<String> = mutableListOf()
        val optionSeparator = "&"

        if (!name.isEmpty()) {
            optionsBuilder.add("field=name&search=$name")
        }

        if (!genre.isEmpty()) {
            optionsBuilder.add("field=genres.name&search=$genre")
        }

        if (!country.isEmpty()) {
            optionsBuilder.add("field=premiere.country&search=$country")
        }

        val actualYear = LocalDate.now().year.toFloat()

        if (startYear > START_YEAR || endYear < actualYear) {
            optionsBuilder.add("field=year&search=${startYear.toInt()}-${endYear.toInt()}")
        }

        if (startPopularity > START_POPULARITY || endPopularity < END_POPULARITY) {
            optionsBuilder.add(
                "field=rating.kp&search=${startPopularity.toInt()}-${endPopularity.toInt()}"
            )
        }

        return optionsBuilder.joinToString(optionSeparator)
    }
}