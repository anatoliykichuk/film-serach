package ru.geekbrains.filmserach.ui.pages.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentSettingsBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.DEFAULT_THEME
import ru.geekbrains.filmserach.ui.base.BaseFragment

const val KEY_CLICK_SAVE_THEME = "KEY_CLICK_SAVE_THEME"
const val ARG_CLICK_SAVE_THEME = "ARG_CLICK_SAVE_THEME"
const val ARG_THEME = "SettingThemeFragment.ARG_THEME"

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    private lateinit var genresRecyclerView: RecyclerView

    private val adapter = CheckedGenresAdapter(object : OnCheckedGenreChanged {
        override fun onCheckedGenresChanged(checkedGenres: List<String>) {
            viewModel.saveCheckedGenres(checkedGenres)
        }
    })

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun initView() {
        genresRecyclerView = binding.genreTypes
        binding.genreTypes.adapter = adapter
        binding.genreTypes.isEnabled = false

        binding.themeGroups.setOnCheckedChangeListener { group, checkedId ->
            val theme = if (checkedId == R.id.lime_theme) {
                Theme.LIME_THEME
            } else {
                Theme.KINOPOISK_THEME
            }
            viewModel.saveTheme(theme!!.key)

            val data: Bundle = Bundle().apply {
                putInt(ARG_CLICK_SAVE_THEME, theme.key)
            }
            parentFragmentManager.setFragmentResult(KEY_CLICK_SAVE_THEME, data)
        }
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
        initThemeButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener()
    }

    private fun initThemeButton() {
        val themeKey = arguments?.getInt(ARG_THEME)
        val theme = if (themeKey == null) {
            DEFAULT_THEME
        } else {
            enumValues<Theme>().forEach {
                if (themeKey == it.key) {
                    it
                }
            }
            DEFAULT_THEME
        }

        val isLimeThemeChecked = (binding.limeTheme.id == theme.buttonId)
        binding.limeTheme.isChecked = isLimeThemeChecked
        binding.kinopoiskTheme.isChecked = !isLimeThemeChecked
    }
}

