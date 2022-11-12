package ru.geekbrains.filmserach.model.entities

import android.graphics.Bitmap
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val title: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val genreIds: List<Int> = listOf<Int>(),
    val releaseDate: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val video: Boolean = false,
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Int = 0,
    val poster: Bitmap? = null,
    val posterPath: String? = "",
    val backdropPath: String? = ""
) : Parcelable {
    companion object : Parceler<Film> {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun create(parcel: Parcel): Film {
            return Film(
                title = parcel.readString()!!,
                originalTitle = parcel.readString()!!,
                originalLanguage = parcel.readString()!!,
                genreIds = parcel.readIntList(),
                releaseDate = parcel.readString()!!,
                adult = parcel.readByte() != 0.toByte(),
                overview = parcel.readString()!!,
                video = parcel.readByte() != 0.toByte(),
                popularity = parcel.readDouble(),
                voteCount = parcel.readInt(),
                voteAverage = parcel.readInt(),
                poster = parcel.readParcelable(Bitmap::class.java.classLoader, Bitmap::class.java),
                posterPath = parcel.readString()!!,
                backdropPath = parcel.readString()!!
            )
        }

        override fun Film.write(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
            parcel.writeString(originalTitle)
            parcel.writeString(originalLanguage)
            parcel.writeIntList(genreIds)
            parcel.writeString(releaseDate)
            parcel.writeByte(if (adult) 1 else 0)
            parcel.writeString(overview)
            parcel.writeByte(if (video) 1 else 0)
            parcel.writeDouble(popularity)
            parcel.writeInt(voteCount)
            parcel.writeInt(voteAverage)
            parcel.writeParcelable(poster, flags)
            parcel.writeString(posterPath)
            parcel.writeString(backdropPath)
        }
    }
}

fun Parcel.readIntList(): List<Int> {
    val size = readInt()
    val intList = mutableListOf<Int>(size)

    for (index in 0 until size) {
        intList.add(readInt())
    }
    return intList
}

fun Parcel.writeIntList(intList: List<Int>) {
    writeInt(intList.size)
    intList.forEach(this::writeInt)
}
