package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.polyactiveteam.polyactive.MainActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before

abstract class BaseTest {
    private lateinit var launchActivity: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        launchActivity = launchActivity()
        
    }

    @After
    fun closeAll() {
        launchActivity.close()
    }

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