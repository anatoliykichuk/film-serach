package ru.geekbrains.filmserach.ui.pages.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmserach.databinding.ItemGenreBinding

class CheckedGenresAdapter(private var onCheckedGenreChanged: OnCheckedGenreChanged?) :
    RecyclerView.Adapter<CheckedGenresAdapter.GenresViewHolder>() {

    private var checkedGenres: MutableList<String> = mutableListOf()

    private var genres: List<String> = listOf()

    fun setGenres(allGenres: List<String>, savedGenres: List<String>) {
        checkedGenres = savedGenres.toMutableList()
        genres = allGenres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GenresViewHolder {
        val binding = ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    inner class GenresViewHolder(
        private val binding: ItemGenreBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: String) {
            binding.genreName.text = genre
            binding.genreCheck.isChecked = checkedGenres.contains(genre)
            //binding.genreCheck.setOnCheckedChangeListener - не использовать!!! работает криво
            binding.genreCheck.setOnClickListener {
                val chb = it as CheckBox
                if (chb.isChecked) {
                    checkedGenres.add(genre)
                }
                else {
                    checkedGenres.remove(genre)
                }
                onCheckedGenreChanged?.onCheckedGenresChanged(checkedGenres.toList())
            }
        }
    }

    fun removeListener() {
        onCheckedGenreChanged = null
    }
}