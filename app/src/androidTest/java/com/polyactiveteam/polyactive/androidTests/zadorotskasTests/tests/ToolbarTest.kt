package com.polyactiveteam.polyactive.androidTests.zadorotskasTests.tests

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.polyactiveteam.polyactive.MainActivity
import com.polyactiveteam.polyactive.R
import com.polyactiveteam.polyactive.androidTests.zadorotskasTests.screens.LoginScreen
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test

class ToolbarTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Логинимся -> попадаем на ленту новостей -> идем в настройки ->
    // -> меняем тему -> проверяем, что заголовок у Toolbar не поменялся
    // (сейчас есть баг и он меняется...)
    @Test
    fun checkToolbarAfterThemeSwitch() {
        val settingsScreen = LoginScreen()
            .login()
            .goToSettings()

        checkActionBarTitle()
        settingsScreen.switchTheme()
        checkActionBarTitle()
    }

    private fun checkActionBarTitle() {
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar")))
        )
            .check(matches(withText(R.string.menu_title_settings)))
    }
}