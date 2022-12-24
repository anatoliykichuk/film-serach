package ru.geekbrains.filmserach.ui.pages.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.data.SEARCH_OPTIONS
import ru.geekbrains.filmserach.databinding.FragmentFilmListBinding
import ru.geekbrains.filmserach.domain.SearchOptions
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.adapters.FilmListAdapter

class FilmListFragment : Fragment() {

    private val viewModel: FilmListViewModel by viewModels {
        FilmListViewModelFactory(activity?.applicationContext)
    }

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
            viewLifecycleOwner,
            Observer { renderFilms(it) }
        )

        if (_searchOptions == null) {
            viewModel.getFavorites()
        }
        else {
            viewModel.getFound(searchOptions) // TODO: подумать, как передать параметры поиска
        }
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
                recyclerView.layoutManager = GridLayoutManager(
                    activity?.applicationContext, 3)

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