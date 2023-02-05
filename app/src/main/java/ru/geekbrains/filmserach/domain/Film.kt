package ru.geekbrains.filmserach.domain

import android.os.Parcel
import android.os.Parcelable

data class Film(
    val title: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val genres: List<String> = listOf<String>(),
    val countries: List<String> = listOf<String>(),
    val releaseDate: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val video: Boolean = false,
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Int = 0,
    val posterPath: String? = "",
    val backdropPath: String? = "",
    var isFavorite: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeStringList(genres)
        parcel.writeStringList(countries)
        parcel.writeString(releaseDate)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(popularity)
        parcel.writeInt(voteCount)
        parcel.writeInt(voteAverage)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}
