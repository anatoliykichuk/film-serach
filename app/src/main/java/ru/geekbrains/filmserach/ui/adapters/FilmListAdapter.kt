package ru.geekbrains.filmserach.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.domain.Film
import ru.geekbrains.filmserach.ui.OnFilmClickListener

class FilmListAdapter(
    private val films: List<Film>
    ): RecyclerView.Adapter<FilmListAdapter.FilmListViewHolder>() {

    private var film = Film()

    class FilmListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.poster)
        val titleView: TextView = itemView.findViewById(R.id.title)
        val originalTitleView: TextView = itemView.findViewById(R.id.original_title)
        val releaseDateView: TextView = itemView.findViewById(R.id.release_date)
        val popularityView: TextView = itemView.findViewById(R.id.popularity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FilmListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_film_list_item, parent, false)

        return FilmListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmListViewHolder, position: Int) {
        film = films[position]

        holder.titleView.text = film.title
        holder.originalTitleView.text = film.originalTitle
        holder.releaseDateView.text = film.releaseDate
        holder.popularityView.text = film.popularity.toString()

        setPoster(holder.posterView, film.posterPath)

        holder.itemView.setOnClickListener {
            val activity = it.context as OnFilmClickListener
            activity.onFilmClick(film)
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

    private fun setPoster(posterView: ImageView, posterPath: String?) {
        if (posterPath == null || posterPath.isEmpty()) {
            posterView.setImageResource(R.drawable.no_poster)
        } else {
            posterView.load(posterPath)
        }
    }
}