package com.polyactiveteam.polyactive.androidTests.tests.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.polyactiveteam.polyactive.R

class ProfilePage : BasePage() {
    private val exitButton = Espresso.onView(ViewMatchers.withId(R.id.button_exit))

    init {
        isLoaded()
    }

    override fun isLoaded() {
        exitButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun exit(): LoginPage {
        exitButton.perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(usualDelay))
        return LoginPage()
    }

    fun checkIsNotLoaded() {
        exitButton.check(ViewAssertions.doesNotExist())
    }
}