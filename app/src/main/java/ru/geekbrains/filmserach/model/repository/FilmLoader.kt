package ru.geekbrains.filmserach.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.example.Docs
import com.example.example.FilmDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import ru.geekbrains.filmserach.viewmodel.DownloadState
import java.io.IOException
import java.security.KeyStore.TrustedCertificateEntry

class FilmLoader(
    private var field: String = "",
    private var search: List<String> = listOf(),
    private val liveData: MutableLiveData<Map<String, List<Docs>>> = MutableLiveData()
) {
    private val filmsLoaded = mutableMapOf<String, List<Docs>>()

    fun load() {
        val client = OkHttpClient()

        requestFilmsByGenres(client)
    }

    private fun requestFilmsByGenres(client: OkHttpClient) {
        for (genre in search) {
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
                        val filmDto = Gson().fromJson(filmJson, FilmDto::class.java)

                        filmsLoaded.put(genre, filmDto.docs)
                        liveData.postValue(filmsLoaded)
                    }
                }
            }
        })
    }
}