package ru.geekbrains.filmserach.ui.pages.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.databinding.FragmentFilmBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.SELECTED_FILM
import java.util.stream.Collectors

const val VIDEO_TITLE = "video_title"
const val VIDEO_URL = "video_url"
const val LOCATION_NAME = "location_name"

class FilmFragment : Fragment() {

    private val viewModel by viewModel<FilmViewModel>()

    private var _binding: FragmentFilmBinding? = null
    private val binding
        get() = _binding!!

    private var _film: Film? = null
    private val film
        get() = _film!!

    companion object {
        fun newInstance() = FilmFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _film = arguments?.getParcelable<Film>(SELECTED_FILM)

        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) {
            setFavoritesTag(it)
            showFilmData()
        }

        viewModel.isFavorite(film)

        setOnClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setOnClickListeners() {
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
        binding.genre.text = listToString(film.genres)
        binding.country.text = listToString(film.countries)
        binding.releaseDate.text = film.releaseDate
        binding.adult.text = film.adult.toString()
        binding.overview.text = film.overview

        PosterLoader.load(binding.poster, film.posterPath)

        val favoritesTagButton: ImageButton = binding.favoritesTag
        setFavoritesTagIcon(favoritesTagButton, film.isFavorite)
    }

    private fun listToString(list: List<String>): String {
        return list.stream().collect(Collectors.joining(", "))
    }

    private fun setFavoritesTag(isFavorite: Boolean) {
        film.isFavorite = isFavorite
    }

    private fun setFavoritesTagIcon(favoritesTagButton: ImageButton, isFavorite: Boolean) {
        if (isFavorite) {
            favoritesTagButton.setImageResource(R.drawable.remove_from_favorites_48)
        } else {
            favoritesTagButton.setImageResource(R.drawable.add_to_favorites_48)
        }
    }

    private fun showPlayer(film: Film) {
        if (film.trailers.isEmpty()) {
            return
        }

        val bundle = Bundle()
        bundle.putString(VIDEO_TITLE, film.title)
        bundle.putString(VIDEO_URL, film.trailers.first())

        findNavController().navigate(R.id.player_fragment, bundle)
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