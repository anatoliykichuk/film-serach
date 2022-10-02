package ru.geekbrains.filmserach.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.example.FilmDto
import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import ru.geekbrains.filmserach.model.entities.getAllGenres
import java.io.IOException

class FilmByGenresLoader(
    private val liveData: MutableLiveData<Map<String, List<FilmDto>>> = MutableLiveData()
) {
    private val filmsLoaded = mutableMapOf<String, List<FilmDto>>()
    private val field = "genres.name"
    private val genres = getAllGenres()

    fun load() {
        val client = OkHttpClient()
        doRequest(client)
    }

    private fun doRequest(client: OkHttpClient) {
        for (genre in genres) {
            val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$genre"
            val request = Request.Builder().url(url).build()
            val call = client?.newCall(request)

            readResponse(call, genre)
        }
    }

    private fun readResponse(call: Call, genre: String) {
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body().let {
                        val filmJson = it?.string()
                        val filmDto = Gson().fromJson(filmJson, FilmsDto::class.java)

                        filmsLoaded.put(genre, filmDto.docs)
                        liveData.postValue(filmsLoaded)
                    }
                }
            }
        })
    }
}