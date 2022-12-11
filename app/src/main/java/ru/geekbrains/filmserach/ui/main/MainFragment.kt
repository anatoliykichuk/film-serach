package ru.geekbrains.filmserach.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.AppState
import ru.geekbrains.filmserach.databinding.FragmentMainBinding
import ru.geekbrains.filmserach.ui.adapters.GenresListAdapter

class MainFragment: Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var binding: FragmentMainBinding? = null
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        recyclerView = binding!!.genresList

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer<AppState> { renderFilms(it) }
        )
        viewModel.getFilmsByGenres()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun renderFilms(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding?.loadingProcess?.visibility = View.GONE

                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = GenresListAdapter(appState.filmsByGenres)
            }

            is AppState.Error -> {
                binding?.loadingProcess?.visibility = View.GONE

                viewModel.getFilmsByGenres()
            }

            is AppState.Loading -> {
                binding?.loadingProcess?.visibility = View.VISIBLE
            }
        }
    }
}
