package ru.geekbrains.filmserach.ui.pages.film

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.databinding.FragmentFilmBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.AppState
import ru.geekbrains.filmserach.ui.BaseFragment
import ru.geekbrains.filmserach.ui.SELECTED_FILM
import java.util.stream.Collectors

const val LOCATION_NAME = "location_name"

class FilmFragment : BaseFragment<FragmentFilmBinding>() {

    private val viewModel by viewModel<FilmViewModel>()

    override fun getViewBinding() = FragmentFilmBinding.inflate(layoutInflater)
    override fun getBaseViewBinding() = binding.filmBase

    private var _film: Film? = null
    private val film
        get() = _film!!

    companion object {
        fun newInstance() = FilmFragment()
    }

    override fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state
            -> when (state) {
                is AppState.Success -> {
                    state.data.isFavorite?.let {
                        setFavoritesTag(it)
                        showFilmData()
                    }
                }

                else -> {}
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initData() {

        //_film = arguments?.getParcelable(SELECTED_FILM)deprecated
        _film = arguments?.getParcelable(SELECTED_FILM, Film::class.java)
        viewModel.isFavorite(film)

        val favoritesTagButton: ImageButton = binding.favoritesTag

        favoritesTagButton.setOnClickListener {
            viewModel.changeFavoritesTag(film)
        }

        val playerButton: ImageButton = binding.player

        playerButton.setOnClickListener {
            showPlayer(film)
        }

        val mapMarkerButton: ImageButton = binding.mapMarker

        mapMarkerButton.setOnClickListener {
            showLocation(film.countries)
        }
    }

    private fun showFilmData() {
        binding.title.text = film.title
        binding.originalTitle.text = film.originalTitle
        binding.popularity.text = film.popularity.toString()
        binding.signature.text = listToString(film.genres) + " (${film.releaseDate})"
        binding.country.text = listToString(film.countries)
        if (film.adult) {
            binding.adult.text = film.adult.toString()
        }
        binding.overview.text = film.overview

        PosterLoader.load(binding.poster, film.posterPath)

        val favoritesTagButton: ImageButton = binding.favoritesTag
        setFavoritesTagIcon(favoritesTagButton, film.isFavorite)
    }

    private fun listToString(list: List<String>): String {
        val strList = list.stream().collect(Collectors.joining(", "))
        return if (list.count() > 1 && strList.length > 0) {
            strList.dropLast(1)
        } else {
            strList
        }
    }

    private fun setFavoritesTag(isFavorite: Boolean) {
        film.isFavorite = isFavorite
    }

    private fun setFavoritesTagIcon(favoritesTagButton: ImageButton, isFavorite: Boolean) {
        if (isFavorite) {
            favoritesTagButton.setImageResource(R.drawable.baseline_favorite_border_24_color)
        } else {
            favoritesTagButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }

    private fun showPlayer(film: Film) {
        if (film.trailers.isEmpty()) {
            return
        }

        val videoUrl = Uri.parse(film.trailers.first())

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(videoUrl)

        startActivity(intent)
    }

    private fun showLocation(countries: List<String>) {
        if (countries.isEmpty()) {
            return
        }

        val bundle = Bundle()
        bundle.putString(LOCATION_NAME, countries.first())

        findNavController().navigate(R.id.maps_fragment, bundle)
    }
}