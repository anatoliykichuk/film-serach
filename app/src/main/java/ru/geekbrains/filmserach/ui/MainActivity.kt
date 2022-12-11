package ru.geekbrains.filmserach.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film

class MainActivity : AppCompatActivity(), OnFilmClickListener {

    private lateinit var filmDatabase: FilmDatabase
    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.getRoot()
        setContentView(view)

        filmDatabase = App.getFilmDatabase(applicationContext)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onFilmClick(film: Film) {
        //val bundle = bundleOf("film" to film)
        val bundle = bundleOf("film" to 1)

        navController.navigate(R.id.film_list_to_details, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}