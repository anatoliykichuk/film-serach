package ru.geekbrains.filmserach.data.db

import androidx.room.TypeConverter
import java.util.stream.Collectors

class CountriesConverter {
    @TypeConverter
    fun fromCountries(countries: List<String>): String {
        return countries.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toCountries(countries: String): List<String> {
        return countries.split(",")
    }
}