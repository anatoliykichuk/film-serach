package ru.geekbrains.filmserach.ui.pages.player

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import ru.geekbrains.filmserach.databinding.FragmentPlayerBinding
import ru.geekbrains.filmserach.ui.pages.film.VIDEO_TITLE
import ru.geekbrains.filmserach.ui.pages.film.VIDEO_URL

class PlayerFragment : Fragment() {

    private var _videoTitle: String? = null
    private val videoTitle
        get() = _videoTitle!!

    private var _videoUrl: String? = null

    private val videoUrl
        get() = _videoUrl!!

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

        arguments?.let {
            _videoTitle = it.getString(VIDEO_TITLE)
            _videoUrl = it.getString(VIDEO_URL)
        }

        showVideoData()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun showVideoData() {
        binding.title.text = videoTitle

        val player = binding.player
        player.setVideoURI(Uri.parse(videoUrl))

        val mediaController = MediaController(context)
        player.setMediaController(mediaController)
        player.start()
    }
}