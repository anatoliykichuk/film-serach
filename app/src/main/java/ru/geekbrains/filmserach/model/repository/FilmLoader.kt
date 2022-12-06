package ru.geekbrains.filmserach.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.example.FilmDto
import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import java.io.IOException

class FilmLoader(
    private var field: String = "",
    private var search: List<String> = listOf(),
    private val liveData: MutableLiveData<Map<String, List<FilmDto>>> = MutableLiveData()
) {
    private val filmsLoaded = mutableMapOf<String, List<FilmDto>>()

    fun load() {
        val client = OkHttpClient()

        doRequest(client)
    }

    private fun doRequest(client: OkHttpClient) {
        for (searchValue in search) {
            val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$searchValue"
            val request = Request.Builder().url(url).build()
            val call = client.newCall(request)

            readResponse(call, searchValue)
        }
    }

    private fun readResponse(call: Call, searchValue: String) {
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body().let {
                        val filmJson = it?.string()
                        val filmDto = Gson().fromJson(filmJson, FilmsDto::class.java)

                        filmsLoaded.put(searchValue, filmDto.films)
                        liveData.postValue(filmsLoaded)
                    }
                }
            }
        })
    }
}