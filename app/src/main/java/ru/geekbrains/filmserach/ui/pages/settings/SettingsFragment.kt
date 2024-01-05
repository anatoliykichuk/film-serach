package ru.geekbrains.filmserach.ui.pages.settings

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentSettingsBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.base.BaseFragment

const val FRAGMENT_RESULT_DATA_KEY = "FRAGMENT_RESULT_DATA_KEY"

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    private lateinit var genresRecyclerView: RecyclerView

    private val adapter = CheckedGenresAdapter(object : OnCheckedGenreChanged {
        override fun onCheckedGenresChanged(checkedGenres: List<String>) {
            viewModel.saveGenres(checkedGenres)
        }
    })

    override fun initView() {
        genresRecyclerView = binding.genreTypes
        binding.genreTypes.adapter = adapter
        binding.genreTypes.isEnabled = false
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { appState ->
            var savedGenres = listOf<String>()

            when (appState) {
                is AppState.Success -> {
                    appState.data.genres?.let {
                        savedGenres = it.filter { genre -> getAllGenres().contains(genre) }
                    }
                }

                else -> {}
            }
            adapter.setGenres(getAllGenres(), savedGenres)
            binding.genreTypes.isEnabled = true
        })
    }

    override fun initData() {
        viewModel.loadSavedGenres()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener()
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}

