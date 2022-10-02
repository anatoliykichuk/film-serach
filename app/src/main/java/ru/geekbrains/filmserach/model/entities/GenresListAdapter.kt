package ru.geekbrains.filmserach.model.entities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R

class GenresListAdapter(private val list: List<String>):
    RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder>() {

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
        val item = list[position]

        holder.genresNameView.text = item

        holder.filmListView.setHasFixedSize(true);
        //holder.filmListView.adapter = FilmListAdapter(item.filmList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}