package ru.geekbrains.filmserach.ui.pages.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentSettingsBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.base.BaseFragment

const val KEY_CLICK_SAVE_THEME = "KEY_CLICK_SAVE_THEME"
const val ARG_CLICK_SAVE_THEME = "ARG_CLICK_SAVE_THEME"
const val ARG_THEME = "SettingThemeFragment.ARG_THEME"

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    private lateinit var genresRecyclerView: RecyclerView

    companion object {
        fun newInstance(key: Int) = SettingsFragment().apply {
            arguments.apply {
                Bundle().apply {
                    putInt(ARG_THEME, key)
                }
            }
        }
    }

    private val adapter = CheckedGenresAdapter(object : OnCheckedGenreChanged {
        override fun onCheckedGenresChanged(checkedGenres: List<String>) {
            viewModel.saveCheckedGenres(checkedGenres)
        }
    })

    override fun initView() {
        genresRecyclerView = binding.genreTypes
        binding.genreTypes.adapter = adapter
        binding.genreTypes.isEnabled = false

        binding.themeGroups.setOnCheckedChangeListener { group, checkedId ->
            val theme = if (checkedId == R.id.lime_theme) { Theme.LIME_THEME }
                                else { Theme.KINOPOISK_THEME }
            viewModel.saveTheme(theme.key)

            val data: Bundle = Bundle().apply {
                putInt(ARG_CLICK_SAVE_THEME, theme.key)
            }
            parentFragmentManager.setFragmentResult(KEY_CLICK_SAVE_THEME, data)
        }
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state
            ->
            var savedGenres = listOf<String>()
            when (state) {
                is AppState.Success -> {
                    state.data.genres?.let {
                        savedGenres = it.filter { x -> getAllGenres().contains(x) }
                    }
                }
                else -> { }
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
}

