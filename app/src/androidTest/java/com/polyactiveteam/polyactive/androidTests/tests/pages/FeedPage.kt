package com.polyactiveteam.polyactive.androidTests.tests.pages

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.polyactiveteam.polyactive.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert

open class FeedPage : BasePage() {
    private val feedButton: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.action_feed))
    private val profileButton = Espresso.onView(ViewMatchers.withId(R.id.action_profile))
    val newsList: NewsList = NewsList()

    init {
        isLoaded()
    }

    final override fun isLoaded() {
        feedButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun goToProfilePage(): ProfilePage {
        profileButton.perform(click())
        Espresso.onView(isRoot()).perform(waitFor(usualDelay))
        return ProfilePage()
    }

    class NewsList {
        private val newsList = Espresso.onView(ViewMatchers.withId(R.id.news_list))
        private val countInitializer = CountInitializer()

        fun countNews(): Int {
            newsList.perform(countInitializer)
            return countInitializer.getInitialCount()
        }

        fun getNewsAtIndex(index: Int): News {
            newsList
                .perform(
                    RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(index, ViewActions.click())
                )
            return News()
        }

        fun checkCurrentNewsCountEqualsTo(count: Int) {
            newsList.check(RecyclerViewItemCountAssertion(count))
        }

        private class CountInitializer : ViewAction {
            private var initialCount = 0

            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(
                    ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
                )
            }

            override fun getDescription(): String {
                return "Initialize count"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                initialCount = adapter!!.itemCount
            }

            fun getInitialCount(): Int {
                return initialCount
            }
        }

        class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                Assert.assertEquals(expectedCount, adapter!!.itemCount)
            }
        }
    }
}
