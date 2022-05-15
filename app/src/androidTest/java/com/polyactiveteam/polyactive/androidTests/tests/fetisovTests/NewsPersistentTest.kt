package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.polyactiveteam.polyactive.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test


class NewsPersistentTest : BaseTest() {
    /**
     * Открываем приложение -> логинимся -> заходим в новости -> подсчитываем кол-во новостей
     * -> выбираем первую новость-> выходим из нее -> проверяем, что кол-во новостей осталось тем же.
     */
    @Test
    fun newsPersistentTest() {
        val loginButton = onView(withId(R.id.shadow_button))
        loginButton
            .check(matches(isDisplayed()))
        loginButton.perform(click())
        onView(isRoot()).perform(waitFor(bigDelay))

        val feedButton = onView(withId(R.id.action_feed))
        feedButton.check(matches(isDisplayed()))
        feedButton.perform(click())
        onView(isRoot()).perform(waitFor(usualDelay))

        var initialCount = 0

        class CountInitializer : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(
                    withEffectiveVisibility(Visibility.VISIBLE)
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

        }

        val newsList = onView(withId(R.id.news_list))
        newsList.perform(CountInitializer())

        if (initialCount == 0) {
            throw IllegalStateException("No news in Feed Fragment")
        }
        newsList
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.news_viewer_toolbar)).check(matches(isDisplayed()))

        Espresso.pressBack()
        newsList.check(RecyclerViewItemCountAssertion(initialCount))
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