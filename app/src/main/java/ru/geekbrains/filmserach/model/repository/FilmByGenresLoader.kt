package ru.geekbrains.filmserach.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.*
import java.io.IOException

class FilmByGenresLoader(
    private val liveData: MutableLiveData<Map<String, List<Film>>> = MutableLiveData()
) {
    private val filmsLoaded = mutableMapOf<String, List<Film>>()
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
            val call = client.newCall(request)

            readResponse(call, genre)
        }
    }

    private fun readResponse(call: Call, genre: String) {
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //TODO("Not yet implemented")
                Log.d("readResponse_onFailure", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body().let {
                        val filmsJson = it?.string()
                        val filmsDto = Gson().fromJson(filmsJson, FilmsDto::class.java)
                        val films = FilmConverter.convertList(filmsDto.films)

                        filmsLoaded[genre] = films
                        liveData.postValue(filmsLoaded)
                    }
                }
            }
        })
    }
}