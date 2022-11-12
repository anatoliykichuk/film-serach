package ru.geekbrains.filmserach.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.*
import ru.geekbrains.filmserach.viewmodel.MainViewModel

class MainFragment: Fragment() {

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
        viewModel.getFilmsByGenres()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun renderFilms(filmsByGenres: Map<String, List<Film>>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = GenresListAdapter(filmsByGenres)
    }
}
