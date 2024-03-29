package ru.geekbrains.filmserach

import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.pages.film.FilmFragment

@RunWith(AndroidJUnit4::class)
class FilmFragmentTest {

    private lateinit var scenario: FragmentScenario<FilmFragment>

    @Before
    fun setUp() {
        scenario = FragmentScenario.launch(FilmFragment::class.java)
    }

    fun fragment_IsNotNull() {
        scenario.onFragment {
            assertNotNull(it)
        }
    }

    @Test
    fun fragmentButtons_AreVisible() {
        scenario.onFragment { fragment ->
            fragment.activity?.let { activity ->
                val favoritesTagButton = activity.findViewById<ImageButton>(R.id.favorites_tag)
                assertEquals(View.VISIBLE, favoritesTagButton?.visibility)

                val mapMarkerButton = activity.findViewById<ImageButton>(R.id.map_marker)
                assertEquals(View.VISIBLE, mapMarkerButton?.visibility)
            }
        }
    }
}