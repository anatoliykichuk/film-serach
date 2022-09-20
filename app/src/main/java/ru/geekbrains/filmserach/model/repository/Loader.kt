package ru.geekbrains.filmserach.model.repository

import com.example.example.Docs
import com.example.example.FilmDto
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.filmserach.model.entities.END_POINT
import ru.geekbrains.filmserach.model.entities.PATH
import ru.geekbrains.filmserach.model.entities.TOKEN
import java.io.IOException

object Loader {
    var field: String = ""
    var search = listOf<String>()

    fun load() : Map<String, List<Docs>> {
        val mutableLoaded = mutableMapOf<String, List<Docs>>()
        val client = OkHttpClient()

        for (searchValue in search) {
            val url = "$PATH/$END_POINT?token=$TOKEN&field=$field&search=$searchValue"
            val request = Request.Builder().url(url).build()
            val call = client?.newCall(request)

            call?.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        response.body().let {
                            val json = it?.string()
                            val dto = Gson().fromJson(json, FilmDto::class.java)
                            mutableLoaded.put(searchValue, dto.docs)
                        }
                    }
                }
            })
        }

        val loaded: Map<String, List<Docs>> = mutableLoaded;
        return loaded
    }
}