package ru.geekbrains.filmserach.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.adapters.OnFilmClickListener

const val SELECTED_FILM = "selected_film"

class MainActivity : AppCompatActivity(), OnFilmClickListener {

    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.getRoot()

        setContentView(view)
        initializeNavController()
        setOnItemMenuListener()
    }

    override fun onFilmClick(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_FILM, film)
        navController.navigate(R.id.film_fragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun initializeNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    private fun setOnItemMenuListener() {
        val mainMenu: BottomNavigationView = binding.mainMenu

        mainMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list -> {
                    navController.navigate(R.id.film_list_by_genres_fragment)
                }

                R.id.menu_favorites -> {
                    navController.navigate(R.id.film_list_fragment)
                }

                R.id.menu_search -> {
                    navController.navigate(R.id.search_options_fragment)
                }

                R.id.menu_settings -> {
                    navController.navigate(R.id.settings_fragment)
                }
            }
            true
        }
    }
}
