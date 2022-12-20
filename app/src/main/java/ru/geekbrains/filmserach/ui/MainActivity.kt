package ru.geekbrains.filmserach.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.data.SELECTED_FILM

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
        setupMainMenu() // TODO: не понятно, почему не работает навигацию по меню
        setOnItemMenuListener()
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

    private fun initializeNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    private fun setupMainMenu() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.menu_list, R.id.menu_favorites, R.id.menu_search)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        val mainMenu: BottomNavigationView = binding.mainMenu
        mainMenu.setupWithNavController(navController)
    }

    private fun setOnItemMenuListener() {
        val mainMenu: BottomNavigationView = binding.mainMenu
        mainMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list -> {

                }
                R.id.menu_favorites -> {
                    navController.navigate(R.id.film_list_to_favorites)
                }
                R.id.menu_search -> {

                }
            }
            true
        }
    }
}
