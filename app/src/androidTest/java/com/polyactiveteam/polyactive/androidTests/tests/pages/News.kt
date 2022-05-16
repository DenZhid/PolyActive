package com.polyactiveteam.polyactive.androidTests.tests.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.polyactiveteam.polyactive.R

class News : LoadableComponent {
    init {
        isLoaded()
    }

    override fun isLoaded() {
        Espresso.onView(ViewMatchers.withId(R.id.news_viewer_toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}