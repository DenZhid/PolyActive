package com.polyactiveteam.polyactive.androidTests.zadorotskasTests.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.polyactiveteam.polyactive.R

class FeedScreen {
    init {
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()))
    }

    fun getButtonFeed(): ViewInteraction = onView(withId(R.id.action_feed))
    fun getButtonProfile(): ViewInteraction = onView(withId(R.id.action_profile))

    fun goToSettings(): SettingsScreen {
        onView(withId(R.id.action_settings))
            .check(matches(isDisplayed()))
            .perform(click())

        return SettingsScreen()
    }
}
