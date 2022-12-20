package ru.geekbrains.filmserach.ui.pages.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.databinding.FragmentFavoritesFilmListBinding

class FavoritesFilmListFragment : Fragment() {

    private val viewModel: FavoritesFilmListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentFavoritesFilmListBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = FavoritesFilmListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesFilmListBinding.inflate(inflater, container, false)
        recyclerView = binding.favoritesList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}