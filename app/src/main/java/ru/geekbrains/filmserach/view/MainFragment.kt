package ru.geekbrains.filmserach.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.example.FilmDto
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.*
import ru.geekbrains.filmserach.viewmodel.MainViewModel
import kotlin.math.log

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = root.findViewById<RecyclerView>(R.id.genres_list)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer<Map<String, List<Film>>> { renderFilms(it) }
        )
        viewModel.getFilmsByGenre()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun renderFilms(films: Map<String, List<Film>>) {
        setGenresList(recyclerView)
    }

    private fun setGenresList(recyclerView: RecyclerView) {
        //recyclerView.setHasFixedSize(true)
        //recyclerView.adapter = GenresListAdapter(getAllGenres())
    }
}