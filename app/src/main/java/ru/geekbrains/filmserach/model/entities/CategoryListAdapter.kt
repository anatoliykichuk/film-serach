package ru.geekbrains.filmserach.model.entities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.R

class CategoryListAdapter(private val list: List<Category>):
    RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    class CategoryListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val categoryNameView: TextView = itemView.findViewById(R.id.category_name)
        val filmListView: RecyclerView = itemView.findViewById(R.id.film_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.film_list, parent, false)

        return CategoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val item = list[position]

        holder.categoryNameView.text = item.name

        holder.filmListView.setHasFixedSize(true);
        holder.filmListView.adapter = FilmListAdapter(item.filmList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}