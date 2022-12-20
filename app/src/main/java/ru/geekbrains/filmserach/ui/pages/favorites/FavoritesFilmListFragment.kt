package ru.geekbrains.filmserach.ui.pages.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.databinding.FragmentFavoritesFilmListBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.adapters.FilmListAdapter

class FavoritesFilmListFragment : Fragment() {

    private val viewModel: FavoritesFilmListViewModel by viewModels {
        FavoritesFilmListViewModelFactory(activity?.applicationContext)
    }

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentFavoritesFilmListBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = FavoritesFilmListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesFilmListBinding.inflate(inflater, container, false)
        recyclerView = binding.favoritesList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { renderFilms(it) }
        )
        viewModel.getFavorites()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun renderFilms(appState: AppState) {
        when(appState) {
            is AppState.SuccessGettingFavoritesFilms -> {
                binding.loadingProcess.visibility = View.GONE

                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = FilmListAdapter(appState.films)
            }

            is AppState.SuccessGettingFilmsByGenre -> {
                binding.loadingProcess.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                binding.loadingProcess.visibility = View.GONE

                viewModel.getFavorites()
            }

            is AppState.Loading -> {
                binding.loadingProcess.visibility = View.VISIBLE
            }
        }
    }
}