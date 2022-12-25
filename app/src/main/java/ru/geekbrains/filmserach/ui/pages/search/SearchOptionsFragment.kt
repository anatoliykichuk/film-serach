package ru.geekbrains.filmserach.ui.pages.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.RangeSlider
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.*
import ru.geekbrains.filmserach.databinding.FragmentSearchOptionsBinding
import ru.geekbrains.filmserach.domain.SearchOptions
import java.time.LocalDate

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
        setYearsOptions()
        setPopularityOptions()

        binding.startSearching.setOnClickListener {
            StartSearching()
        }

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

        ArrayAdapter<String>(
            context, android.R.layout.simple_list_item_1, genres
        ).also {
            genreView.setAdapter(it)
        }
    }

    private fun setCountriesList(context: Context) {
        val counties = getAllCountries().values.toTypedArray()
        val countiesView = binding.country as AutoCompleteTextView
        countiesView.threshold = 1

        ArrayAdapter<String>(
            context, android.R.layout.simple_list_item_1, counties
        ).also {
            countiesView.setAdapter(it)
        }
    }

    private fun setYearsOptions() {
        val yearsView = binding.years as RangeSlider
        var endYear = LocalDate.now().year.toFloat()
        val rest = (endYear - START_YEAR) % YEARS_FOR_STEP

        if (rest > 0) {
            endYear += (YEARS_FOR_STEP - rest)
        }

        yearsView.stepSize = YEARS_FOR_STEP
        yearsView.valueFrom = START_YEAR
        yearsView.valueTo = endYear
        yearsView.values = listOf<Float>(START_YEAR, endYear)
    }

    private fun setPopularityOptions() {
        val popularityView = binding.popularity as RangeSlider
        popularityView.stepSize = POPULARITY_FOR_STEP
        popularityView.valueFrom = START_POPULARITY
        popularityView.valueTo = END_POPULARITY
        popularityView.values = listOf<Float>(START_POPULARITY, END_POPULARITY)
    }

    private fun StartSearching() {
        val searchOptions = SearchOptions(
            name = binding.name.text.toString(),
            genre = binding.genre.text.toString(),
            country = binding.country.text.toString(),
            startYearDefault = binding.years.valueFrom,
            startYear = binding.years.values[0],
            endYearDefault = binding.years.valueTo,
            endYear = binding.years.values[1],
            startPopularityDefault = binding.popularity.valueFrom,
            startPopularity = binding.popularity.values[0],
            endPopularityDefault = binding.popularity.valueTo,
            endPopularity = binding.popularity.values[1]
        )

        val bundle = Bundle()
        bundle.putParcelable(SEARCH_OPTIONS, searchOptions)

        findNavController().navigate(R.id.search_options_to_found, bundle)
    }
}