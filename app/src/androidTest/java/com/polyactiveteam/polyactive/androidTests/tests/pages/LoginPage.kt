package com.polyactiveteam.polyactive.androidTests.tests.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.polyactiveteam.polyactive.R

class LoginPage : BasePage() {
    private var loginButton: ViewInteraction = Espresso
        .onView(ViewMatchers.withId(R.id.shadow_button))

    init {
        isLoaded()
    }

    override fun isLoaded() {
        loginButton
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun login(): FeedPage {
        loginButton.perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(bigDelay))
        return FeedPage()
    }
}