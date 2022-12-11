package ru.geekbrains.filmserach.data.net

import ru.geekbrains.filmserach.domain.*
import androidx.lifecycle.MutableLiveData
import com.example.example.FilmsDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.AppState
import ru.geekbrains.filmserach.data.FilmConverter

class FilmByGenresLoader(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) {
    private val filmsLoaded = mutableMapOf<String, List<Film>>()
    private val field = "genres.name"
    private val genres = getAllGenres()

    fun load() {
        val client = OkHttpClient()
        doRequest(client)
    }

    private fun doRequest(client: OkHttpClient) {
        Thread {
            for (genre in genres) {
                val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$genre"
                val request = Request.Builder().url(url).build()
                val call = client.newCall(request)
                val response = call.execute()

                readResponse(response, genre)
            }

            liveData.postValue(AppState.Success(filmsLoaded))
        }.start()
    }

    private fun readResponse(response: Response, genre: String) {
        if (!response.isSuccessful) {
            return
        }

        response.body().let {
            val filmsJson = it?.string()
            val filmsDto = Gson().fromJson(filmsJson, FilmsDto::class.java)
            val films = FilmConverter.convertListFromDto(filmsDto.films)

            filmsLoaded[genre] = films
        }
    }
}