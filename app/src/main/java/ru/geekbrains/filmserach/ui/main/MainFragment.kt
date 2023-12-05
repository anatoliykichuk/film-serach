package ru.geekbrains.filmserach.ui.main

import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

import ru.geekbrains.filmserach.databinding.FragmentMainBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.BaseFragment
import ru.geekbrains.filmserach.ui.adapters.GenresListAdapter

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)
    override fun getBaseViewBinding() =  binding.mainBase

    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun initView() {
        recyclerView = binding.genresList
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    showLoading(false)
                    it.data.filmsByGenres?.let {
                        recyclerView.adapter = GenresListAdapter(it)
                    }
                }
                is AppState.Loading -> {
                    showLoading(true)
                }
                is AppState.Error -> {
                    showLoading(false)
                    viewModel.getFilmsByGenres()
                }
                else -> {
                    showLoading(false)
                }
            }
        }
    }

    override fun initData() {
        viewModel.getFilmsByGenres()
    }
}
