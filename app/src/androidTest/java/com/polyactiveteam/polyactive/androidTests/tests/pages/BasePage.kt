package com.polyactiveteam.polyactive.androidTests.tests.pages

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

abstract class BasePage : LoadableComponent {
    companion object {
        const val bigDelay: Long = 3000
        const val usualDelay: Long = 1000

        @JvmStatic
        protected fun waitFor(delay: Long): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
                override fun getDescription(): String = "wait for $delay milliseconds"
                override fun perform(uiController: UiController, v: View?) {
                    uiController.loopMainThreadForAtLeast(delay)
                }
            }
        }
    }
}