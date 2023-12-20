package ru.geekbrains.filmserach.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.databinding.ActivityMainBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.adapters.OnFilmClickListener
import ru.geekbrains.filmserach.ui.pages.settings.ARG_CLICK_SAVE_THEME
import ru.geekbrains.filmserach.ui.pages.settings.KEY_CLICK_SAVE_THEME

const val SELECTED_FILM = "selected_film"

class MainActivity : AppCompatActivity(), OnFilmClickListener {

    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val model: MainViewModel by inject()
        viewModel = model
        viewModel.getLiveData().observe(this, Observer { state -> initView(state) })
        viewModel.loadPreferences()
    }

    private fun initView(state: AppState) {
        if (state is AppState.Success) {
            state.data.theme?.let {
                setTheme(it.theme)
            }
        }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.getRoot()
        setContentView(view)

        initializeNavController()
        setOnItemMenuListener()
    }

    private fun initializeNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navHostFragment.childFragmentManager.setFragmentResultListener(
            KEY_CLICK_SAVE_THEME, this
        ) { _, result ->
            val keyTheme = result.getInt(ARG_CLICK_SAVE_THEME)
            navHostFragment.childFragmentManager.clearFragmentResultListener(KEY_CLICK_SAVE_THEME)

            recreate()
        }
    }

    override fun onFilmClick(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_FILM, film)
        navController.navigate(R.id.film_fragment, bundle)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
