package ru.geekbrains.filmserach.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.viewmodel.FilmViewModel
import ru.geekbrains.filmserach.viewmodel.MainViewModel

class FilmFragment : Fragment() {

    private val viewModel: FilmViewModel by viewModel()

    companion object {
        fun newInstance() = FilmFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
    }

}