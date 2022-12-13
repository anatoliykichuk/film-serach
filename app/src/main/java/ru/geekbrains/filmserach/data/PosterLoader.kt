package ru.geekbrains.filmserach.data

import android.widget.ImageView
import coil.load
import ru.geekbrains.filmserach.R

object PosterLoader {
    fun load(posterView: ImageView, posterPath: String?) {
        if (posterPath == null || posterPath.isEmpty()) {
            posterView.setImageResource(R.drawable.no_poster)
        } else {
            posterView.load(posterPath)
        }
    }
}