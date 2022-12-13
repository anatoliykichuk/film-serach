package ru.geekbrains.filmserach.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.core.component.getScopeName
import ru.geekbrains.filmserach.App
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.db.FilmDatabase
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.domain.SELECTED_FILM

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
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_FILM, film)
        navController.navigate(R.id.film_list_to_details, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}