package ru.geekbrains.filmserach.ui.pages.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.databinding.FragmentSearchOptionsBinding

class SearchOptionsFragment : Fragment() {

    private val viewModel: SearchOptionsViewModel by viewModel()
    private var _binding: FragmentSearchOptionsBinding?  = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = SearchOptionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchOptionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}