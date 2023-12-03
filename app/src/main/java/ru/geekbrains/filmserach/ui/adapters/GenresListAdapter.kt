package ru.geekbrains.filmserach.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.data.getAllGenres
import ru.geekbrains.filmserach.databinding.FragmentFilmListByGenresBinding
import ru.geekbrains.filmserach.domain.Film

class GenresListAdapter(
    private val filmsByGenres: Map<String, List<Film>>,
    private val genres: List<String> = getAllGenres()
    ): RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder>() {

    class GenresListViewHolder(
        private val binding: FragmentFilmListByGenresBinding
    ): RecyclerView.ViewHolder(binding.root) {

        val filmListView: RecyclerView = binding.filmListFragment

        fun bind(filmsByGenres: Map<String, List<Film>>, genre: String) {
            binding.genreName.text = genre

            filmListView.adapter = filmsByGenres[genre]?.let {
                FilmListAdapter(it.toList())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListViewHolder {
        val binding = FragmentFilmListByGenresBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GenresListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresListViewHolder, position: Int) {
        holder.bind(filmsByGenres, genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}