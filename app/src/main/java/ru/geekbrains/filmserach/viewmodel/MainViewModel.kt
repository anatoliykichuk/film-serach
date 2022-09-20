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
import ru.geekbrains.filmserach.model.repository.Loader
import java.io.IOException

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getFilms() {
        val loader = Loader
        loader.field = "genres.name"
        loader.search = getAllGenres()
        val loaded = loader.load()

        val i = 0
    }



}