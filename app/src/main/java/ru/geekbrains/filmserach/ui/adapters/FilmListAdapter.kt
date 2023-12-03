package ru.geekbrains.filmserach.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.databinding.FragmentFilmListItemBinding
import ru.geekbrains.filmserach.domain.Film

class FilmListAdapter(
    private val films: List<Film>
) : RecyclerView.Adapter<FilmListAdapter.FilmListViewHolder>() {

    class FilmListViewHolder(
        private val binding: FragmentFilmListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.title.text = film.title
            binding.releaseDate.text = film.releaseDate
            binding.popularitySmall.text = film.popularity.toString()

            val posterView: ImageView = binding.poster
            PosterLoader.load(posterView, film.posterPath)

            binding.root.setOnClickListener {
                val activity = it.context as OnFilmClickListener
                activity.onFilmClick(film)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FilmListViewHolder {

        val binding = FragmentFilmListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilmListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmListViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }
}