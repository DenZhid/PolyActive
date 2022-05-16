package com.polyactiveteam.polyactive.androidTests.tests.fetisovTests

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.polyactiveteam.polyactive.MainActivity
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

}