package ru.geekbrains.filmserach.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.geekbrains.filmserach.databinding.FragmentBaseBinding

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {

    protected abstract fun getViewBinding() : VBinding
    protected var _binding: VBinding? = null
    protected val binding
        get() = _binding!!

    open fun getBaseViewBinding() : FragmentBaseBinding? { return null }
    private var _baseBinding : FragmentBaseBinding? = null

    open fun initView() {}
    open fun observeData() {}
    open fun initData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = getViewBinding()
        _baseBinding = getBaseViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
        _baseBinding = null
    }

    fun showLoading(isLoading: Boolean) {
        _baseBinding?.loadingProcess?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}