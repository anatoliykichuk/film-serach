package ru.geekbrains.filmserach.ui.pages.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.data.getAllCountries
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentSearchOptionsBinding

class SearchOptionsFragment : Fragment() {

    private val viewModel: SearchOptionsViewModel by viewModel()
    private var _binding: FragmentSearchOptionsBinding?  = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = SearchOptionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchOptionsBinding.inflate(inflater, container, false)

        val context: Context = requireActivity().applicationContext

        setGenresList(context)
        setCountriesList(context)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setGenresList(context: Context) {
        val genres = getAllGenres().toTypedArray()
        val genreView = binding.genre as AutoCompleteTextView
        genreView.threshold = 1

        ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, genres).also {
            genreView.setAdapter(it)
        }
    }

    private fun setCountriesList(context: Context) {
        val counties = getAllCountries().values.toTypedArray()
        val countiesView = binding.country as AutoCompleteTextView
        countiesView.threshold = 1

        ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, counties).also {
            countiesView.setAdapter(it)
        }
    }
}