package ru.geekbrains.filmserach.model.entities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R

class FilmListByCategoryAdapter(private val list: List<Film>):
    RecyclerView.Adapter<FilmListByCategoryAdapter.FilmListByCategoryViewHolder>() {

    class FilmListByCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val categoryNameView: TextView = itemView.findViewById(R.id.category_name)
        val filmListView: RecyclerView = itemView.findViewById(R.id.film_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListByCategoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.film_list_item, parent, false)

        return FilmListByCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmListByCategoryViewHolder, position: Int) {
        val item = list[position]

        //holder.categoryNameView.text = item.category
        //TODO: Добавить инициализацию RecyclerView
    }

    override fun getItemCount(): Int {
        return list.size
    }
}