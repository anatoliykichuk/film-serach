package ru.geekbrains.filmserach.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import ru.geekbrains.filmserach.model.entities.getAllGenres
import java.io.IOException
import kotlin.math.log

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getFilms() {
        val client = OkHttpClient()
        val request = Request.Builder().url(url()).build()
        val call = client?.newCall(request)

        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val j = 0
            }

            var dataByJSON = ""

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        dataByJSON = it.toString()

                        Log.i("AAA", dataByJSON)
                        val i = 0

                    }
                }
            }
        })
    }

    private fun url(): String {
        val genres = getAllGenres().toList()
        val url = "$PATH/$END_POINT?token=$TOKEN&field=genres.name&search=${genres[0].second}"
        return url
    }
}