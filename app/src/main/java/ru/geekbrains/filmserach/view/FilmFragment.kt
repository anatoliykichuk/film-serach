package ru.geekbrains.filmserach.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.viewmodel.FilmViewModel

class FilmFragment : Fragment() {

    companion object {
        fun newInstance() = FilmFragment()
    }

    private lateinit var viewModel: FilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}