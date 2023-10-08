package ru.geekbrains.filmserach

import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.runner.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.pages.film.FilmFragment

@RunWith(AndroidJUnit4::class)
class FilmFragmentTest {

    lateinit var scenario: FragmentScenario<FilmFragment>

    @Before
    fun setUp() {
        scenario = FragmentScenario.launch(FilmFragment::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    fun fragment_AssertIsNotNull() {
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