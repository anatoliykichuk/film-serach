package ru.geekbrains.filmserach.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.domain.getAllGenres

class GenresListAdapter(
    private val filmsByGenres: Map<String, List<Film>>,
    private val genres: List<String> = getAllGenres()
    ): RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder>() {

    class GenresListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val filmListView: RecyclerView = itemView.findViewById(R.id.film_list)

        fun bind(filmsByGenres: Map<String, List<Film>>, genre: String) {
            (itemView.findViewById(R.id.genre_name) as TextView).text = genre

            filmListView.setHasFixedSize(true);
            filmListView.adapter = filmsByGenres[genre]?.let { FilmListAdapter(it.toList()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_film_list, parent, false)

        return GenresListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenresListViewHolder, position: Int) {
        holder.bind(filmsByGenres, genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}