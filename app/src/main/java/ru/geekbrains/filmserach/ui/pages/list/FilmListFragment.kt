package ru.geekbrains.filmserach.ui.pages.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.databinding.FragmentFilmListBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.adapters.FilmListAdapter
import ru.geekbrains.filmserach.ui.pages.search.SEARCH_OPTIONS

const val FILMS_ON_ROW_COUNT = 3

class FilmListFragment : Fragment() {

    private val viewModel by viewModel<FilmListViewModel>()

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentFilmListBinding? = null
    private val binding
        get() = _binding!!

    private var _searchOptions: SearchOptions? = null
    private val searchOptions
        get() = _searchOptions!!

    companion object {
        fun newInstance() = FilmListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        recyclerView = binding.favoritesList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _searchOptions = arguments?.getParcelable(SEARCH_OPTIONS)

        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { renderFilms(it) }

        if (_searchOptions == null) {
            viewModel.getFavorites()
        } else {
            viewModel.getFound(searchOptions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun renderFilms(appState: AppState) {
        when (appState) {
            is AppState.SuccessGettingFavoritesFilms -> {
                binding.loadingProcess.visibility = View.GONE

                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = GridLayoutManager(
                    activity?.applicationContext, FILMS_ON_ROW_COUNT
                )

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