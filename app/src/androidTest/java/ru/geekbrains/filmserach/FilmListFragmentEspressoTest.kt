package ru.geekbrains.filmserach

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.adapters.GenresListAdapter
import ru.geekbrains.filmserach.ui.pages.list.FilmListFragment

@RunWith(AndroidJUnit4::class)
class FilmListFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<FilmListFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun fragmentRecyclerView_scrollTo() {
        onView(withId(R.id.genre_name)).perform(
            RecyclerViewActions.scrollTo<GenresListAdapter.GenresListViewHolder>(
                hasDescendant(withText("фэнтези"))
            )
        )
    }
}