package ru.geekbrains.filmserach.model.entities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R

class FilmListAdapter(private val list: List<Film>):
    RecyclerView.Adapter<FilmListAdapter.FilmListViewHolder>() {

    class FilmListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.poster)
        val titleView: TextView = itemView.findViewById(R.id.title)
        val originalTitleView: TextView = itemView.findViewById(R.id.original_title)
        val releaseDateView: TextView = itemView.findViewById(R.id.release_date)
        val popularityView: TextView = itemView.findViewById(R.id.popularity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FilmListAdapter.FilmListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_film_list_item, parent, false)

        return FilmListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmListAdapter.FilmListViewHolder, position: Int) {
        val item = list[position]

        holder.posterView.setImageResource(item.poster)
        holder.titleView.text = item.title
        holder.originalTitleView.text = item.originalTitle
        holder.releaseDateView.text = item.releaseDate
        holder.popularityView.text = item.popularity.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}