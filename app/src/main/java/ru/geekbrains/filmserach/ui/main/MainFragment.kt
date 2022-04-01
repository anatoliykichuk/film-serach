package ru.geekbrains.filmserach.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.model.entities.CategoryListAdapter
import ru.geekbrains.filmserach.model.example.CategoryListExample

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.category_list)
        setCategoryList(recyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setCategoryList(recyclerView: RecyclerView) {
        val list = CategoryListExample.list

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CategoryListAdapter(list)
    }
}