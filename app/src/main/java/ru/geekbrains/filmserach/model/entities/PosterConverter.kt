package ru.geekbrains.filmserach.model.entities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class PosterConverter {
    @TypeConverter
    fun fromPoster(poster: Bitmap?): ByteArray? {
        val stream = ByteArrayOutputStream()
        poster?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    @TypeConverter
    fun toPoster(poster: ByteArray?): Bitmap? {
        return poster?.let { BitmapFactory.decodeByteArray(poster, 0, poster.size) }
    }
}