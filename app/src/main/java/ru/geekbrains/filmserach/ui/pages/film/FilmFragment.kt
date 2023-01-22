package ru.geekbrains.filmserach.ui.pages.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.data.SELECTED_FILM
import ru.geekbrains.filmserach.databinding.FragmentFilmBinding
import ru.geekbrains.filmserach.domain.Film
import java.util.stream.Collectors

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
            viewLifecycleOwner,
            Observer<Boolean> {
                setFavoritesTag(it)
            }
        )

        viewModel.isFavorite(film)

        val favoritesTagButton: ImageButton = binding.favoritesTag

        setFavoritesTagIcon(favoritesTagButton, film)
        showFilmData()

        favoritesTagButton.setOnClickListener {
            viewModel.changeFavoritesTag(film)
            setFavoritesTagIcon((it as ImageButton), film)
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

    private fun setFavoritesTag(isFavorite: Boolean) {
        film.isFavorite = isFavorite
    }

    private fun setFavoritesTagIcon(favoritesTagButton: ImageButton, film: Film) {
        if (film.isFavorite) {
            favoritesTagButton.setImageResource(R.drawable.remove_from_favorites)
        } else {
            favoritesTagButton.setImageResource(R.drawable.add_to_favorites)
        }
    }
}