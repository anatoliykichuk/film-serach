package ru.geekbrains.filmserach.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.example.FilmDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import ru.geekbrains.filmserach.model.entities.getAllGenres
import java.io.IOException

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getFilms() {
        val client = OkHttpClient()
        val request = Request.Builder().url(url()).build()
        val call = client?.newCall(request)

        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("CALL", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val filmJson = it.string()
                        setFilmDto(filmJson)
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

    private fun setFilmDto(filmJson: String) {
        if (filmJson.isEmpty() || filmJson.toList()[0].toString() != "{") {
            return
        }

        val filmDto = Gson().fromJson(filmJson, FilmDto::class.java)
        val i = 0
    }
}