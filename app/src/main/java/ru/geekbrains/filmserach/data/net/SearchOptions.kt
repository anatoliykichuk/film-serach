package ru.geekbrains.filmserach.data.net

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.geekbrains.filmserach.data.getSelectedFields

@Parcelize
data class SearchOptions(
    val name: String,
    val genre: String,
    val country: String,
    val startYearDefault: Float,
    val startYear: Float,
    val endYearDefault: Float,
    val endYear: Float,
    val startPopularityDefault: Float,
    val startPopularity: Float,
    val endPopularityDefault: Float,
    val endPopularity: Float
) : Parcelable {

    override fun toString(): String {
        val optionsBuilder: MutableList<String> = mutableListOf()
        val optionSeparator = "&"

        if (name.isNotEmpty()) {
            optionsBuilder.add("field=name&search=$name")
        }

        if (genre.isNotEmpty()) {
            optionsBuilder.add("field=genres.name&search=$genre")
        }

        if (country.isNotEmpty()) {
            optionsBuilder.add("field=premiere.country&search=$country")
        }

        if (startYear > startYearDefault || endYear < endYearDefault) {
            optionsBuilder.add("field=year&search=${startYear.toInt()}-${endYear.toInt()}")
        }

        if (startPopularity > startPopularityDefault || endPopularity < endPopularityDefault) {
            optionsBuilder.add(
                "field=rating.kp&search=${startPopularity.toInt()}-${endPopularity.toInt()}"
            )
        }

        optionsBuilder.add(
            "selectFields=${getSelectedFields()}"
        )

        if (optionsBuilder.isNotEmpty()) {
            optionsBuilder.add(0, "")
        }

        return optionsBuilder.joinToString(optionSeparator)
    }
}