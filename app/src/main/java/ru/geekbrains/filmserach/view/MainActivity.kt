package ru.geekbrains.filmserach.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.model.entities.Film
import ru.geekbrains.filmserach.model.entities.OnFilmClickListener
import ru.geekbrains.filmserach.model.entities.SELECTED_FILM_DATA
import ru.geekbrains.filmserach.model.repository.FilmDatabase

class MainActivity : AppCompatActivity(), OnFilmClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmDatabase: FilmDatabase
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.getRoot()
        setContentView(view)

        filmDatabase = App.getFilmDatabase(applicationContext)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment

        //navController = Navigation.findNavController(this, R.id.fragment_container)
        navController = navHostFragment.navController

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onFilmClick(film: Film) {
        /*
        val bundle = bundleOf("film" to 1)
        //val bundle = bundleOf("film" to film)

        supportFragmentManager.setFragmentResult(
            SELECTED_FILM_DATA, bundle)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FilmFragment.newInstance())
            .addToBackStack(null)
            .commit()
        */

        navController.navigate(R.id.film_list_to_details)
    }
}