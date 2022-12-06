package ru.geekbrains.filmserach.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import androidx.core.os.bundleOf
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.OnFilmClickListener
import ru.geekbrains.filmserach.model.entities.SELECTED_FILM_DATA

class MainActivity : AppCompatActivity(), OnFilmClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onFilmClick(film: Film) {
        val bundle = bundleOf("film" to 1)

        supportFragmentManager.setFragmentResult(
            SELECTED_FILM_DATA, bundle)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FilmFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}