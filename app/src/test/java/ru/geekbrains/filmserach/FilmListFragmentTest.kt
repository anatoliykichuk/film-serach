package ru.geekbrains.filmserach

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.runner.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.pages.list.FilmListFragment

@RunWith(AndroidJUnit4::class)
class FilmListFragmentTest {

    private lateinit var scenario: FragmentScenario<FilmListFragment>

    @Before
    fun setUp() {
        scenario = FragmentScenario.launch(FilmListFragment::class.java)
    }

    @Test
    fun fragment_InNotNull() {
        scenario.onFragment {
            assertNotNull(it)
        }
    }

    @Test
    fun fragmentRecyclerView_IsVisible() {
        scenario.onFragment { fragment ->
            fragment.activity?.let { activity ->
                val recyclerView = activity.findViewById<RecyclerView>(R.id.favorites_list)
                assertEquals(View.VISIBLE, recyclerView.visibility)
            }
        }
    }

    @Test
    fun fragmentProgressBar_isGone() {
        scenario.onFragment { fragment ->
            fragment.activity?.let { activity ->
                val recyclerView = activity.findViewById<FrameLayout>(R.id.loading_process)
                assertEquals(View.GONE, recyclerView.visibility)
            }
        }
    }
}