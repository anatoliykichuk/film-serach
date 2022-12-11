package ru.geekbrains.filmserach.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film

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
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onFilmClick(film: Film) {
        /*
        val bundle = bundleOf("film" to 1)
        //val bundle = bundleOf("film" to film)

        supportFragmentManager.setFragmentResult(
            SELECTED_FILM_DATA, bundle)
        */

        navController.navigate(R.id.film_list_to_details)
    }
}