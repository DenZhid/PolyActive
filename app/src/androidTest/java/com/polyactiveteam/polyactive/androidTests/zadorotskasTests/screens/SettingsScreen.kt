package com.polyactiveteam.polyactive.androidTests.zadorotskasTests.screens

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.polyactiveteam.polyactive.R

class SettingsScreen {
    init {
        onView(withId(R.id.theme_tv)).check(matches(isDisplayed()))
    }

    fun switchTheme() {
        onView(withId(R.id.theme_iv2)).perform(click())
    }
}