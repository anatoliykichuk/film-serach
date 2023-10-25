package ru.geekbrains.filmserach


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.geekbrains.filmserach.ui.MainActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityRecordedTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityRecordedTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.film_list_fragment),
                childAtPosition(
                    withClassName(`is`("androidx.appcompat.widget.LinearLayoutCompat")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val imageButton = onView(
            allOf(
                withId(R.id.favorites_tag),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.appcompat.widget.LinearLayoutCompat::class.java))),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.favorites_tag),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.appcompat.widget.LinearLayoutCompat")),
                        9
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.menu_favorites), withContentDescription("���������"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_menu),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val imageView = onView(
            allOf(
                withId(R.id.poster),
                withParent(withParent(withId(R.id.film_list_item))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.menu_search), withContentDescription("�����"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_menu),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val autoCompleteTextView = onView(
            allOf(
                withId(R.id.name),
                withParent(withParent(withId(R.id.search_options_fragment))),
                isDisplayed()
            )
        )
        autoCompleteTextView.check(matches(isDisplayed()))

        val materialAutoCompleteTextView = onView(
            allOf(
                withId(R.id.name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.search_options_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialAutoCompleteTextView.perform(replaceText("007"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.start_searching), withText("�����"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.search_options_fragment),
                        5
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val imageView2 = onView(
            allOf(
                withId(R.id.poster),
                withParent(withParent(withId(R.id.film_list_item))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
