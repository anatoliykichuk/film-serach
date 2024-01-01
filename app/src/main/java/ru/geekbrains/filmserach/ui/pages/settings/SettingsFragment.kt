package ru.geekbrains.filmserach.ui.pages.settings

import android.os.Bundle
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentSettingsBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.DEFAULT_THEME
import ru.geekbrains.filmserach.ui.base.BaseFragment

const val FRAGMENT_RESULT_DATA_KEY = "FRAGMENT_RESULT_DATA_KEY"
const val SELECTED_THEME_KEY = "SELECTED_THEME_KEY"
const val SAVED_THEME_KEY = "SAVED_THEME_KEY"

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    private lateinit var genresRecyclerView: RecyclerView

    private val adapter = CheckedGenresAdapter(object : OnCheckedGenreChanged {
        override fun onCheckedGenresChanged(checkedGenres: List<String>) {
            viewModel.saveGenres(checkedGenres)
        }
    })

    private val onCheckedChangeListener =
        RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val themeKey = if (checkedId == R.id.lime_theme) {
                Theme.LIME_THEME.key
            } else {
                Theme.KINOPOISK_THEME.key
            }

            viewModel.saveTheme(themeKey)

            val data: Bundle = Bundle().apply {
                putInt(SELECTED_THEME_KEY, themeKey)
            }
            parentFragmentManager.setFragmentResult(FRAGMENT_RESULT_DATA_KEY, data)
        }

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
        initThemeButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener()
    }

    private fun initThemeButton() {
        val themeKey = arguments?.getInt(SAVED_THEME_KEY)
        val theme = if (themeKey == null) {
            DEFAULT_THEME
        } else {
            getThemeByKey(themeKey)
        }

        val isLimeThemeChecked = (binding.limeTheme.id == theme.buttonId)
        binding.limeTheme.isChecked = isLimeThemeChecked
        binding.kinopoiskTheme.isChecked = !isLimeThemeChecked

        binding.themeGroups.setOnCheckedChangeListener(onCheckedChangeListener)
    }

    private fun getThemeByKey(themeKey: Int): Theme {
        enumValues<Theme>().forEach {
            if (themeKey == it.key) {
                return it
            }
        }
        return DEFAULT_THEME
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}

