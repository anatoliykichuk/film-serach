package ru.geekbrains.filmserach.ui.pages.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    private val viewModel by viewModel<PlayerViewModel>()

    private var _binding: FragmentPlayerBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = PlayerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}