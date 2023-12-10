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
        val selectFieldsSeparator = "&selectFields="

        if (name.isNotEmpty()) {
            optionsBuilder.add("query=$name")
        } else {
            addUniversalSearchOptions(optionsBuilder)
        }

        optionsBuilder.add(
            "selectFields=${getSelectedFields().joinToString(selectFieldsSeparator)}"
        )

        return optionsBuilder.joinToString(optionSeparator)
    }

    private fun addUniversalSearchOptions(optionsBuilder: MutableList<String>) {
        if (genre.isNotEmpty()) {
            optionsBuilder.add("genres.name=$genre")
        }

        if (country.isNotEmpty()) {
            optionsBuilder.add("countries.name=$country")
        }

        if (startYear > startYearDefault || endYear < endYearDefault) {
            optionsBuilder.add("releaseYears.start=${startYear.toInt()}")
            optionsBuilder.add("releaseYears.end=${endYear.toInt()}")
        }

        if (startPopularity > startPopularityDefault || endPopularity < endPopularityDefault) {
            optionsBuilder.add(
                "rating.imdb=${startPopularity.toInt()}-${endPopularity.toInt()}"
            )
        }
    }
}