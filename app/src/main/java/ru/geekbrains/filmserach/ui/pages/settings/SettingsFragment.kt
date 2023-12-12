package ru.geekbrains.filmserach.ui.pages.settings

import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.core.util.contains
import androidx.core.view.get
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.databinding.FragmentSettingsBinding
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun initView() {
        val genresAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_multiple_choice, viewModel.allGenres
        )
        binding.genreTypes.adapter = genresAdapter
        binding.genreTypes.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        binding.genreTypes.isEnabled = false
        binding.genreTypes.setOnItemClickListener { parent, view, position, id ->
            binding.genreTypes.isEnabled.let {
                val checkedItems = binding.genreTypes.checkedItemPositions
                if (checkedItems.contains(position)) {
                    val txtGenre = binding.genreTypes.get(position) as AppCompatCheckedTextView
                    val genre = txtGenre.text.toString()
                    if (checkedItems.get(position)) {
                        viewModel.addGenre(genre)
                    }
                    else {
                        viewModel.removeGenre(genre)
                    }
                }
            }
        }
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state
            ->
            when (state) {
                is AppState.Success -> {
                    state.data.genres?.let { genres ->
                        for (genre in genres) {
                            val iCheck = viewModel.allGenres.indexOf(genre)
                            val txtCheck = binding.genreTypes.get(iCheck) as AppCompatCheckedTextView
                            txtCheck.isChecked = true
                            binding.genreTypes.checkedItemPositions.append(iCheck, true)
                        }
                        binding.genreTypes.isEnabled = true
                    }
                }
                else -> {}
            }
        })
    }

    override fun initData() {
        viewModel.loadSavedGenres()
    }
}

