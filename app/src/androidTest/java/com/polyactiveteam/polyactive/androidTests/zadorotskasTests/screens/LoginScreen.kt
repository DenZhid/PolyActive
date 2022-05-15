package com.polyactiveteam.polyactive.androidTests.zadorotskasTests.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.polyactiveteam.polyactive.R

class LoginScreen {
    init {
        onView(withId(R.id.app_name)).check(matches(isDisplayed()))
    }

    fun login(): FeedScreen {
        onView(withId(R.id.shadow_button))
            .check(matches(isDisplayed()))
            .perform(click())

        return FeedScreen()
    }
}