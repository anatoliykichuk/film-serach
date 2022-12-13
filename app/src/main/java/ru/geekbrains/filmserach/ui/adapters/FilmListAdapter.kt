package ru.geekbrains.filmserach.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.PosterLoader
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.OnFilmClickListener

class FilmListAdapter(
    private val films: List<Film>
    ): RecyclerView.Adapter<FilmListAdapter.FilmListViewHolder>() {

    class FilmListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            (itemView.findViewById(R.id.title) as TextView).text = film.title
            (itemView.findViewById(R.id.original_title) as TextView).text = film.originalTitle
            (itemView.findViewById(R.id.release_date) as TextView).text = film.releaseDate
            (itemView.findViewById(R.id.popularity) as TextView).text = film.popularity.toString()

            val posterView: ImageView = itemView.findViewById(R.id.poster)
            PosterLoader.load(posterView, film.posterPath)

            itemView.setOnClickListener {
                val activity = it.context as OnFilmClickListener
                activity.onFilmClick(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FilmListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_film_list_item, parent, false)

        return FilmListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmListViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }
}