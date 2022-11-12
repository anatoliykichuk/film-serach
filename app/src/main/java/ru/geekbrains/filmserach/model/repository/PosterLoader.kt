package ru.geekbrains.filmserach.model.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request

class PosterLoader {
    fun load(url: String): Bitmap? {
        var poster: Bitmap? = null

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)
        val response = call.execute()

        if (!response.isSuccessful) {
            return poster
        }

        response.body()?.let {
            val inputStream = it.byteStream()
            poster = BitmapFactory.decodeStream(inputStream)
        }
        return poster
    }
}