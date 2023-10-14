package ru.geekbrains.filmserach

import androidx.navigation.testing.TestNavHostController
import ru.geekbrains.filmserach.ui.MainActivity

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.pages.list.FilmListFragment

//import androidx.fragment.app.testing.FragmentScenario

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun navigateToFilmListFragment() {
        //val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        //val filmListScenario = launchFragmentInContainer<FilmListFragment>()

        onView(withId(R.id.film_list_fragment)).perform(click()).check(matches(isChecked()))
    }
}