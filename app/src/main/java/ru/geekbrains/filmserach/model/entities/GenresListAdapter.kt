package ru.geekbrains.filmserach.model.entities

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R

class GenresListAdapter(
    private val films: Map<String, List<Film>>,
    private val genres: List<String> = getAllGenres()
    ): RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder>() {

    class GenresListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val genresNameView: TextView = itemView.findViewById(R.id.genre_name)
        val filmListView: RecyclerView = itemView.findViewById(R.id.film_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_film_list, parent, false)

        return GenresListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenresListViewHolder, position: Int) {
        val item = genres[position]

        holder.genresNameView.text = item

        holder.filmListView.setHasFixedSize(true);
        holder.filmListView.adapter = films[item]?.let { FilmListAdapter(it.toList()) }
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}