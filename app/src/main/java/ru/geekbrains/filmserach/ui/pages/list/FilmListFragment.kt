package ru.geekbrains.filmserach.ui.pages.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

import ru.geekbrains.filmserach.data.net.SearchOptions
import ru.geekbrains.filmserach.databinding.FragmentFilmListBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.BaseFragment
import ru.geekbrains.filmserach.ui.adapters.FilmListAdapter
import ru.geekbrains.filmserach.ui.pages.search.SEARCH_OPTIONS

const val FILMS_ON_ROW_COUNT = 3

class FilmListFragment : BaseFragment<FragmentFilmListBinding>() {

    private val viewModel by viewModel<FilmListViewModel>()

    override fun getViewBinding() = FragmentFilmListBinding.inflate(layoutInflater)
    override fun getBaseViewBinding() = binding.filmListBase

    private lateinit var recyclerView: RecyclerView

    private var _searchOptions: SearchOptions? = null
    private val searchOptions
        get() = _searchOptions!!

    companion object {
        fun newInstance() = FilmListFragment()
    }

    override fun initView() {
        recyclerView = binding.favoritesList
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    showLoading(false)

                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = GridLayoutManager(
                        activity?.applicationContext, FILMS_ON_ROW_COUNT
                    )
                    it.data.films?.let {
                        recyclerView.adapter = FilmListAdapter(it)
                    }
                }
                is AppState.Loading -> {
                    showLoading(true)
                }
                is AppState.Error -> {
                    showLoading(false)
                    viewModel.getFavorites()
                }
                else -> {
                    showLoading(false)
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initData() {
        //_searchOptions = arguments?.getParcelable(SEARCH_OPTIONS)deprecated
        _searchOptions = arguments?.getParcelable(SEARCH_OPTIONS, SearchOptions::class.java)
        if (_searchOptions == null) {
            viewModel.getFavorites()
        } else {
            viewModel.getFound(searchOptions)
        }
    }
}