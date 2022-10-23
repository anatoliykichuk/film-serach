package ru.geekbrains.filmserach.model.repository

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.geekbrains.filmserach.R

class PosterLoader {
    fun load(url: String): Bitmap? {
        var poster: Bitmap? = BitmapFactory.decodeResource(
            Resources.getSystem(), R.drawable.no_poster
        )

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