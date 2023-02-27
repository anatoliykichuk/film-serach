package ru.geekbrains.filmserach.data

import android.widget.ImageView
import coil.load
import ru.geekbrains.filmserach.R

const val EMPTY_POSTER_PATH = R.drawable.no_poster

object PosterLoader {
    fun load(posterView: ImageView, posterPath: String?) {
        if (posterPath == null || posterPath.isEmpty()) {
            posterView.setImageResource(EMPTY_POSTER_PATH)
        } else {
            posterView.load(posterPath)
        }
    }
}