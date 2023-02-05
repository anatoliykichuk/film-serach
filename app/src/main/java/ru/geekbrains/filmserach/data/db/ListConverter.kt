package ru.geekbrains.filmserach.data.db

import androidx.room.TypeConverter
import java.util.stream.Collectors

class ListConverter {
    @TypeConverter
    fun fromCountries(list: List<String>): String {
        return list.stream().collect(Collectors.joining(", "))
    }

    @TypeConverter
    fun toCountries(list: String): List<String> {
        return list.split(",")
    }
}