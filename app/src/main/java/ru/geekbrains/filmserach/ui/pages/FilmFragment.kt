package ru.geekbrains.filmserach.ui.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.databinding.FragmentFilmBinding
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.data.SELECTED_FILM
import java.util.stream.Collectors

class FilmFragment: Fragment() {

    private lateinit var viewModel: FilmViewModel

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

        viewModel = ViewModelProviders
            .of(this, FilmViewModelFactory(activity?.applicationContext))
            .get(FilmViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _film = arguments?.getParcelable<Film>(SELECTED_FILM)
        film.isFavorite = viewModel.isFavorite(film)

        showFilmData()

        binding.addToFavorites.setOnClickListener {
            (it as ImageButton).setImageResource(
                if (film.isFavorite) {
                    R.drawable.add_to_favorites
                } else {
                    R.drawable.remove_from_favorites
                }
            )

            viewModel.changeFavoritesTag(film)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun showFilmData() {
        binding.title.text = film.title
        binding.originalTitle.text = film.originalTitle
        binding.popularity.text = film.popularity.toString()
        binding.genre.text = film.genres.stream().collect(Collectors.joining(", "))
        binding.releaseDate.text = film.releaseDate
        binding.adult.text = film.adult.toString()
        binding.overview.text = film.overview

        PosterLoader.load(binding.poster, film.posterPath)
    }
}